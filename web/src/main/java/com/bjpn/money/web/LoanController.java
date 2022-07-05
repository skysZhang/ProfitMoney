package com.bjpn.money.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.bjpn.money.model.BidInfo;
import com.bjpn.money.model.LoanInfo;
import com.bjpn.money.model.PageModel;
import com.bjpn.money.service.BidInfoService;
import com.bjpn.money.service.LoanInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LoanController {

    @Reference(interfaceClass = LoanInfoService.class, version = "1.0.0", timeout = 20000)
    LoanInfoService loanInfoService;
    @Reference(interfaceClass = BidInfoService.class, version = "1.0.0", timeout = 20000)
    BidInfoService bidInfoService;

    @GetMapping("/loan/loan")
    public String loan(@RequestParam(name = "ptype", required = true) Integer ptype, Model model, Long curPage, HttpServletRequest request) {

        //1、展现产品
//        Map<String, Object> parasMap = new HashMap<>();
//        parasMap.put("ptype", ptype);
//        parasMap.put("start", 0);
//        parasMap.put("content", 9);
//        List<LoanInfo> loanInfoList = loanInfoService.queryLoanInfoByTypeAndNum(parasMap);
        PageModel pageModel = (PageModel) request.getSession().getAttribute("pageModel");
        if (pageModel == null) {
            pageModel = new PageModel(9);
            request.getSession().setAttribute("pageModel", pageModel);
        }

        //如果没有指定哪一页，默认首页
        if (curPage == null || curPage < 1) {
            curPage = pageModel.getFirstPage().longValue();
        }

        //查询总记录数
        Long totalCount = loanInfoService.queryLoanInfoCountByType(ptype);
        //设置总记录数
        pageModel.setTotalCount(totalCount);
        pageModel.setTotalPage(totalCount / pageModel.getPageSize() + 1);

        //课后：可以在redis中存放部分数据，一旦超出界限，直接访问redis设置好的数据，降低数据库压力

        //超出尾页，访问尾页的数据
        if (pageModel.getTotalPage() < curPage) {
            curPage = pageModel.getTotalPage();
        }

        pageModel.setCurPage(curPage);

        List<LoanInfo> loanInfoList = loanInfoService.queryLoanInfoByTypeAndPageModel(ptype, pageModel);
        model.addAttribute("loanInfoList", loanInfoList);
        model.addAttribute("ptype", ptype);

        //2、todo：投资排行榜

        return "loan";
    }

    //课后：doFirst(), doUp(){curPage--}, doDown(){curPage++}, doLast(), doGo()

    @GetMapping("/loan/loanInfo")
    public String loanInfo(@RequestParam(name = "loanId") Integer loanId, Model model, HttpServletRequest request) {
        //查询产品信息
        LoanInfo loanInfo = loanInfoService.queryLoanInfoById(loanId);
        model.addAttribute("loanInfo", loanInfo);

        //查询产品的投资记录
        List<BidInfo> bidInfoList = bidInfoService.queryBidInfoById(loanId);
        model.addAttribute("bidInfoList", bidInfoList);

        return "loanInfo";
    }

}
