package com.bjpn.money.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpn.money.model.LoanInfo;
import com.bjpn.money.service.LoanInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoanController {

    @Reference(interfaceClass = LoanInfoService.class, version = "1.0.0", timeout = 20000)
    LoanInfoService loanInfoService;

    @GetMapping("/loan/loan")
    public String loan(@RequestParam(name = "ptype", required = true) Integer ptype, Model model) {

        //1、展现产品
        Map<String, Object> parasMap = new HashMap<>();
        parasMap.put("ptype", ptype);
        parasMap.put("start", 0);
        parasMap.put("content", 9);
        List<LoanInfo> loanInfoList = loanInfoService.queryLoanInfoByTypeAndNum(parasMap);
        model.addAttribute("loanInfoList", loanInfoList);

        //2、todo：投资排行榜

        return "loan";
    }

}
