package com.bjpn.money.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpn.money.mapper.FinanceAccountMapper;
import com.bjpn.money.mapper.UserMapper;
import com.bjpn.money.model.FinanceAccount;
import com.bjpn.money.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 用户业务实现类
 */
@Service(interfaceClass = UserService.class, version = "1.0.0", timeout = 20000)
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    FinanceAccountMapper financeAccountMapper;
    //平台用户数
    @Override
    public Long queryUserCount() {
        return userMapper.selectUserCount();
    }

    //注册：验证手机号码
    @Override
    public int checkPhone(String phone) {
        return userMapper.selectUserCountByPhone(phone);
    }

    @Override
    public User register(String phone, String loginPassword) {
        User user = new User();
        user.setAddTime(new Date());
        user.setLoginPassword(loginPassword);
        user.setPhone(phone);
        int num = userMapper.insertSelective(user);

        if (num == 1) {
            new Thread(() -> {
                FinanceAccount financeAccount = new FinanceAccount();
                financeAccount.setAvailableMoney(888d);

                financeAccount.setUid(user.getId());
                financeAccountMapper.insertSelective(financeAccount);
            }).start();
        }
        return user;
    }
}
