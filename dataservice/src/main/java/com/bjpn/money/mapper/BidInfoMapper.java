package com.bjpn.money.mapper;


import com.bjpn.money.model.BidInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BidInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BidInfo record);

    int insertSelective(BidInfo record);

    BidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BidInfo record);

    int updateByPrimaryKey(BidInfo record);

    Double selectBidMoneySum();

    List<BidInfo> selectBidInfoById(Integer loanId);
}