package com.bjpn.money.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpn.money.mapper.LoanInfoMapper;
import com.bjpn.money.model.LoanInfo;
import com.bjpn.money.model.PageModel;
import com.bjpn.money.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 产品实现业务类
 */
@Service(interfaceClass = LoanInfoService.class, version = "1.0.0", timeout = 20000)
@Component
public class LoanInfoServiceImpl implements LoanInfoService{
    @Autowired
    LoanInfoMapper loanInfoMapper;
    @Autowired(required = false)
    RedisTemplate redisTemplate;

    //查询产品平均利率
    @Override
    public Double queryLoanInfoHistoryRateAvg() {
        Double loanInfoHistoryRateAvg = (Double) redisTemplate.opsForValue().get(Constants.LOAN_INFO_HISTORY_RATE_AVG);
        if (loanInfoHistoryRateAvg == null) {
            System.out.println("--数据库查询--");
            loanInfoHistoryRateAvg = loanInfoMapper.selectLoanInfoHistoryRateAvg();
            redisTemplate.opsForValue().set(Constants.LOAN_INFO_HISTORY_RATE_AVG, loanInfoHistoryRateAvg, 20, TimeUnit.SECONDS);
        } else {
            System.out.println("--缓存命中--");
        }
        return loanInfoHistoryRateAvg;
    }

    //首页：根据产品类型和数量，查询产品信息
    @Override
    public List<LoanInfo> queryLoanInfoByTypeAndNum(Map<String, Object> parasMap) {
        return loanInfoMapper.selectLoanInfoByTypeAndNum(parasMap);
    }

    //列表：根据类型和分页模型，查询数据
    @Override
    public List<LoanInfo> queryLoanInfoByTypeAndPageModel(Integer ptype, PageModel pageModel) {

        Map<String, Object> parasMap = new HashMap<>();
        parasMap.put("ptype", ptype);
        parasMap.put("start", (pageModel.getCurPage() - 1) * pageModel.getPageSize());
        parasMap.put("content", pageModel.getPageSize());
        return loanInfoMapper.selectLoanInfoByTypeAndNum(parasMap);
    }

    //列表：查询总记录数
    @Override
    public Long queryLoanInfoCountByType(Integer ptype) {
        return loanInfoMapper.selectLoanInfoCountByType(ptype);
    }

    //详情：根据id，查询产品信息
    @Override
    public LoanInfo queryLoanInfoById(Integer loanId) {
        return loanInfoMapper.selectByPrimaryKey(loanId);
    }
}
