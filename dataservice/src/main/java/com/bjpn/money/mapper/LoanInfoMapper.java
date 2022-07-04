package com.bjpn.money.mapper;

import com.bjpn.money.model.LoanInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface LoanInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanInfo record);

    int insertSelective(LoanInfo record);

    LoanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanInfo record);

    int updateByPrimaryKey(LoanInfo record);

    //查询产品平均利率
    Double selectLoanInfoHistoryRateAvg();

    //首页：根据产品类型和数量，查询产品信息
    List<LoanInfo> selectLoanInfoByTypeAndNum(Map<String, Object> parasMap);

    //列表：查询总记录数
    Long selectLoanInfoCountByType(Integer ptype);
}