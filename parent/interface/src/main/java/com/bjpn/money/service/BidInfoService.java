package com.bjpn.money.service;

import com.bjpn.money.model.BidInfo;

import java.util.List;

/**
 * 投资业务接口
 */
public interface BidInfoService {
    /**
     * 投资总金额
     * @return
     */
    Double queryBidMoneySum();

    /**
     * 详情：根据产品编号，查询产品投资记录
     * @Param loanId
     * @return
     */
    List<BidInfo> queryBidInfoById(Integer loanId);

}
