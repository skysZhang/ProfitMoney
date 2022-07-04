package com.bjpn.money.model;

import java.io.Serializable;

/**
 * 共20条3页　当前为第2页　 首页 上一页 下一页 尾页:
 * 分析：
 * 不变：总记录数  总页数   当前页   首页  尾页   一页显示多少条===》Map==面向对象的编程思想==》PageModel
 * 变：上一页 下一页
 * <p>
 * 第一页  0-9        (cunPage-1)*pageSize, pageSize
 * 第二页  10-19
 * 第三页  20-29
 * <p>
 * 分析、抽象、封装
 */
public class PageModel implements Serializable {
    private Long totalCount;
    private Long totalPage;
    private Long curPage;
    private Integer firstPage = 1;
    private Integer pageSize = 10;

    public PageModel(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageModel() {
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Long totalPage) {
        this.totalPage = totalPage;
    }

    public Long getCurPage() {
        return curPage;
    }

    public void setCurPage(Long curPage) {
        this.curPage = curPage;
    }

    public Integer getFirstPage() {
        return firstPage;
    }

    public void setFirstPage(Integer firstPage) {
        this.firstPage = firstPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
