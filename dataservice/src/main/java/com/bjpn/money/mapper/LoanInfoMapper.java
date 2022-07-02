package com.bjpn.money.mapper;

import com.bjpn.money.model.LoanInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LoanInfo record);

    int insertSelective(LoanInfo record);

    LoanInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LoanInfo record);

    int updateByPrimaryKey(LoanInfo record);

    Double selectLoanInfoHistoryRateAvg();
}