package com.bjpn.money.service;

import com.bjpn.money.model.LoanInfo;

import java.util.List;
import java.util.Map;

/**
 * 产品业务接口
 */
public interface LoanInfoService {
    /**
     * 查询产品平均利率
     * @return
     */
    Double queryLoanInfoHistoryRateAvg();

    /**
     * 首页：根据产品类型和数量，查询产品信息
     * @param parasMap
     * @return
     */
    List<LoanInfo> queryLoanInfoByTypeAndNum(Map<String, Object> parasMap);
}
