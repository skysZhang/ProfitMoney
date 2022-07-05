package com.bjpn.money.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpn.money.mapper.BidInfoMapper;
import com.bjpn.money.model.BidInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 投资业务实现类
 */
@Service(interfaceClass = BidInfoService.class, version = "1.0.0", timeout = 20000)
@Component
public class BidInfoServiceImpl implements BidInfoService{
    @Autowired
    BidInfoMapper bidInfoMapper;

    //投资总金额
    @Override
    public Double queryBidMoneySum() {
        return bidInfoMapper.selectBidMoneySum();
    }

    //详情：根据产品编号，查询产品投资记录
    @Override
    public List<BidInfo> queryBidInfoById(Integer loanId) {
        return bidInfoMapper.selectBidInfoById(loanId);
    }

}
