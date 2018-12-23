package com.itheima.domain;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
    private Integer totalPage;//总页数
    private Integer currentPage;//当前页面
    private Integer totalCount;//查询总数
    private List<Users> list ; //封装查询到的用户信息
    private Integer pageRows;//每一页显示的条数
    private Integer start ;//开始的索引
    private Users user;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<Users> getList() {
        return list;
    }

    public void setList(List<Users> list) {
        this.list = list;
    }

    public Integer getPageRows() {
        return pageRows;
    }

    public void setPageRows(Integer pageRows) {
        this.pageRows = pageRows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                ", pageRows=" + pageRows +
                ", start=" + start +
                ", user=" + user +
                '}';
    }
}
