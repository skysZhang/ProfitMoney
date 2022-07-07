package com.bjpn.money.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpn.money.model.User;
import com.bjpn.money.service.UserService;
import com.bjpn.money.utils.Constants;
import com.bjpn.money.utils.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Reference(interfaceClass = UserService.class, version = "1.0.0", timeout = 20000)
    UserService userService;

    @GetMapping("/loan/page/register")
    public String register() {
        return "register";
    }

    //{"code":1,"message":"","success":true}
    @GetMapping("/loan/page/checkPhone")
    @ResponseBody
    public Object checkPhone(@RequestParam(name = "phone", required = true) String phone) {
        System.out.println("--checkPhone--" + phone);
        int num = userService.checkPhone(phone);
        if (num > 0) {
            return Result.error("手机号码已被占用");
        }
        return Result.success();
    }
    
    @PostMapping("/loan/page/registerSubmit")
    @ResponseBody
    public Object registerSubmit(@RequestParam(name = "phone", required = true) String phone,
                                 @RequestParam(name = "loginPassword", required = true)String loginPassword,
                                 HttpServletRequest request) {
        User user = userService.register(phone, loginPassword);
        if (user == null) {
            return Result.error("注册失败");
        }
        request.getSession().setAttribute(Constants.LOGIN_USER, user);
        return Result.success();
    }
}
