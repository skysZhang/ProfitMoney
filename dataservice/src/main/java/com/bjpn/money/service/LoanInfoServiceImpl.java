package com.bjpn.money.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpn.money.mapper.LoanInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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
        Double loanInfoHistoryRateAvg = (Double) redisTemplate.opsForValue().get("loanInfoHistoryRateAvg");
        if (loanInfoHistoryRateAvg == null) {
            System.out.println("--数据库查询--");
            loanInfoHistoryRateAvg = loanInfoMapper.selectLoanInfoHistoryRateAvg();
            redisTemplate.opsForValue().set("loanInfoHistoryRateAvg", loanInfoHistoryRateAvg, 20, TimeUnit.SECONDS);
        } else {
            System.out.println("--缓存命中--");
        }
        return loanInfoHistoryRateAvg;
    }
}
