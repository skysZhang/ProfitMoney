package com.bjpn.money.service;

import com.bjpn.money.model.LoanInfo;
import com.bjpn.money.model.PageModel;

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

    /**
     * 列表：根据类型和分页模型，查询数据
     * @param ptype
     * @param pageModel
     * @return
     */
    List<LoanInfo> queryLoanInfoByTypeAndPageModel(Integer ptype, PageModel pageModel);

    /**
     * 列表：查询总记录数
     * @param ptype
     * @return
     */
    Long queryLoanInfoCountByType(Integer ptype);

    /**
     * 详情：根据id，查询产品信息
     * @param loanId
     * @return
     */
    LoanInfo queryLoanInfoById(Integer loanId);
}
