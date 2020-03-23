package com.wangt.entity;


public class Meeting extends BaseEntity{
  /*
   * id
   */
  private Integer id;
  /*
   * 部门名称
   */
  private String deptName;
  /*
   * 部门id
   */
  private Integer deptId;
  /*
   * 会议标题
   */
  private String title;
  /*
   * 会议内容
   */
  private String content;
  /*
   * 发布时间
   */
  private String publishDate;
  /*
   * 开始时间
   */
  private String startTime;
  /*
   * 结束时间
   */
  private String endTime;
  /*
   * 会议状态 0:未开始 1:进行中 2:已结束
   */
  private Integer status;
  /*
   * 抄送人id，以逗号分隔
   */
  private String makeUser;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDeptName() {
    return deptName;
  }

  public void setDeptName(String deptName) {
    this.deptName = deptName;
  }

  public Integer getDeptId() {
    return deptId;
  }

  public void setDeptId(Integer deptId) {
    this.deptId = deptId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getPublishDate() {
    return publishDate;
  }

  public void setPublishDate(String publishDate) {
    this.publishDate = publishDate;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getMakeUser() {
    return makeUser;
  }

  public void setMakeUser(String makeUser) {
    this.makeUser = makeUser;
  }
}
