package com.wangt.dao;

import com.wangt.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @author wangt
 * @description
 * @date 2020/3/17 22:40
 */
public class LoginDao extends BaseDao{

    public User checkLogin(User user){
        String sql = "select * from user where username=? and password =?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),user.getUsername(),user.getPassword());
    }

    public User findByWeChatOpenid(String openid) {
        String sql = "select * from user where wx_openid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),openid);
    }

    public User findByQqOpenid(String openid) {
        String sql = "select * from user where qq_openid=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), openid);
    }
}
