package com.wangt.entity;

/**
 * @author wangt
 * @description
 * @date 2020/3/18 19:47
 */
public class Page {
    /*
     * 总页数
     */
    private Integer pageCount;
    /*
     * 当前页
     */
    private Integer pageNo;
    /*
     * 每页数
     */
    private Integer pageSize=3;
    /*
     * 总记录数
     */
    private Integer rowCount;

    public Integer getPageCount() {
        return (rowCount-1)/pageSize+1;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getRowCount() {
        return rowCount;
    }

    public void setRowCount(Integer rowCount) {
        this.rowCount = rowCount;
    }

    //获取当前页第一条记录的下标
    public Integer getFirstResult(){
        return (pageNo-1)*pageSize;
    }
}
