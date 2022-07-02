package com.bjpn.money.service;

/**
 * 产品业务接口
 */
public interface LoanInfoService {
    /**
     * 查询产品平均利率
     * @return
     */
    Double queryLoanInfoHistoryRateAvg();
}
