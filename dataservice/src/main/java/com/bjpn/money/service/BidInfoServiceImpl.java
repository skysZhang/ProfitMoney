package com.bjpn.money.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpn.money.mapper.BidInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

}
