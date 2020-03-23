package com.wangt.entity;

/**
 * @author wangt
 * @description
 * @date 2020/3/19 10:53
 */
public class Dept extends BaseEntity {
    /*
     * 部门id
     */
    private Integer id;
    /*
     * 部门名称
     */
    private String name;
    /*
     * 部门人数
     */
    private Integer count;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
