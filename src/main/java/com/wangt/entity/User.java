package com.wangt.entity;


public class User extends BaseEntity {
    /*
     * id
     */
    private Integer id;
    /*
     *部门id
     */
    private Integer deptId;
    /*
     *用户名
     */
    private String username;
    /*
     *密码
     */
    private String password;
    /*
     *邮箱
     */
    private String email;
    /*
     *QQ登录标识符
     */
    private String qqOpenid;
    /*
     *微信登录标识符
     */
    private String wxOpenid;
    /*
     *真实姓名
     */
    private String realName;
    /*
     *年龄
     */
    private Integer age;
    /*
     *手机号
     */
    private String phone;
    /*
     *性别 1:男 0:女
     */
    private String gender;
    /*
     *简介
     */
    private String description;
    /*
     *注册时间
     */
    private String registerTime;
    /*
     *上次登录时间
     */
    private String loginTime;
    /*
     *头像
     */
    private String pic;
    /*
     *查看数
     */
    private Integer look;
    /*
     *是否私密 0：私密 1：公开
     */
    private String isSecret;
    /*
     *创建人
     */
    private String createName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQqOpenid() {
        return qqOpenid;
    }

    public void setQqOpenid(String qqOpenid) {
        this.qqOpenid = qqOpenid;
    }

    public String getWxOpenid() {
        return wxOpenid;
    }

    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getLook() {
        return look;
    }

    public void setLook(Integer look) {
        this.look = look;
    }

    public String getIsSecret() {
        return isSecret;
    }

    public void setIsSecret(String isSecret) {
        this.isSecret = isSecret;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }
}
