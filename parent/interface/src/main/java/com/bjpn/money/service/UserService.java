package com.bjpn.money.service;

import com.bjpn.money.model.User;

/**
 * 用户业务接口
 */
public interface UserService {
    /**
     * 平台用户数
     * @return
     */
    Long queryUserCount();

    int checkPhone(String phone);

    User register(String phone, String loginPassword);
}
