package com.bjpn.money.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpn.money.service.BidInfoService;
import com.bjpn.money.service.LoanInfoService;
import com.bjpn.money.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Reference(interfaceClass = LoanInfoService.class, version = "1.0.0", timeout = 20000)
    LoanInfoService loanInfoService;

    @Reference(interfaceClass = UserService.class, version = "1.0.0", timeout = 20000)
    UserService userService;

    @Reference(interfaceClass = BidInfoService.class, version = "1.0.0", timeout = 20000)
    BidInfoService bidInfoService;

    @GetMapping("/index")
    public String index(Model model) {

        Double loanInfoHistoryRateAvg = loanInfoService.queryLoanInfoHistoryRateAvg();
        model.addAttribute("loanInfoHistoryRateAvg", loanInfoHistoryRateAvg);

        Long userCount = userService.queryUserCount();
        model.addAttribute("userCount", userCount);

        Double bidMoneySum = bidInfoService.queryBidMoneySum();
        model.addAttribute("bidMoneySum", bidMoneySum);

        return "index";
    }

}
