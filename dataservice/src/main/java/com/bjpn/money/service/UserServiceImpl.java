package com.bjpn.money.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.bjpn.money.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户业务实现类
 */
@Service(interfaceClass = UserService.class, version = "1.0.0", timeout = 20000)
@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;
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
}
