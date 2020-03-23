package com.wangt.entity;

import java.io.Serializable;

/**
 * @author wangt
 * @description
 * @date 2020/3/17 22:23
 */
public class BaseEntity implements Serializable {
    public static final long serialVersionUID = 1L;
    /*
     * 创建时间
     */
    private String createTime;

    /*
     * 创建人
     */
    private Integer createBy;

    /*
     * 是否删除 1是，0否
     */
    private Integer delFlag;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
