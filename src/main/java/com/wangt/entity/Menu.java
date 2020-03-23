package com.wangt.entity;


public class Menu {
  /*
   * id
   */
  private Integer id;
  /*
   * 父菜单ID，一级菜单为0
   */
  private Integer pId;
  /*
   * 菜单名称
   */
  private String name;
  /*
   * 菜单URL
   */
  private String url;
  /*
   * 授权(多个用逗号分隔，如：user:list,user:create)
   */
  private String perms;
  /*
   * 类型   0：目录   1：菜单   2：按钮
   */
  private Integer type;
  /*
   * 排序
   */
  private Integer orderBy;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getpId() {
    return pId;
  }

  public void setpId(Integer pId) {
    this.pId = pId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getPerms() {
    return perms;
  }

  public void setPerms(String perms) {
    this.perms = perms;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(Integer orderBy) {
    this.orderBy = orderBy;
  }
}
