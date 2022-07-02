package com.bjpn.money.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpn.money.model.LoanInfo;
import com.bjpn.money.service.BidInfoService;
import com.bjpn.money.service.LoanInfoService;
import com.bjpn.money.service.UserService;
import com.bjpn.money.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //1、动力金融网历史年化收益率
        Double loanInfoHistoryRateAvg = loanInfoService.queryLoanInfoHistoryRateAvg();
        model.addAttribute(Constants.LOAN_INFO_HISTORY_RATE_AVG, loanInfoHistoryRateAvg);

        //2、平台用户数
        Long userCount = userService.queryUserCount();
        model.addAttribute(Constants.USER_COUNT, userCount);

        //3、累计成交额
        Double bidMoneySum = bidInfoService.queryBidMoneySum();
        model.addAttribute(Constants.BID_MONEY_SUM, bidMoneySum);

        Map<String, Object> parasMap = new HashMap<>();
        //4、新手宝
        parasMap.put("ptype", 0);
        parasMap.put("start", 0);
        parasMap.put("content", 1);
        List<LoanInfo> loanInfoList_X = loanInfoService.queryLoanInfoByTypeAndNum(parasMap);
        model.addAttribute("loanInfoList_X", loanInfoList_X);

        //5、优选标
        parasMap.put("ptype", 1);
        parasMap.put("start", 0);
        parasMap.put("content", 4);
        List<LoanInfo> loanInfoList_Y = loanInfoService.queryLoanInfoByTypeAndNum(parasMap);
        model.addAttribute("loanInfoList_Y", loanInfoList_Y);

        //6、散标
        parasMap.put("ptype", 2);
        parasMap.put("start", 0);
        parasMap.put("content", 8);
        List<LoanInfo> loanInfoList_S = loanInfoService.queryLoanInfoByTypeAndNum(parasMap);
        model.addAttribute("loanInfoList_S", loanInfoList_S);

        return "index";
    }

}
