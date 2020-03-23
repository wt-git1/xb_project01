package com.wangt.dao;

import com.wangt.entity.Page;
import com.wangt.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/17 22:30
 */
public class UserDao extends BaseDao{
    public List<User> list(User user, Page page){
        String sql="SELECT " +
                "u.id id, " +
                "u.dept_id deptId, " +
                "u.username username, " +
                "u.email email, " +
                "u.real_name realName, " +
                "u.age age, " +
                "u.phone phone, " +
                "u.gender gender, " +
                "u.is_secret isSecret, " +
                "u.create_time createTime, " +
                "u1.username createName  " +
                "FROM " +
                "user u " +
                "left join user u1 on u1.id=u.create_by where u.username like ? " +
                "limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class),"%"+user.getUsername()+"%",page.getFirstResult(),page.getPageSize());
    }

    public Integer count(User user) {
        String sql = "select count(*) from user where username like ? ";
        return template.queryForObject(sql, Integer.class, "%" + user.getUsername() + "%");
    }

    public User getUserByName(String name) {
        String sql="SELECT " +
                "id, " +
                "dept_id, " +
                "username, " +
                "password, " +
                "email, " +
                "qq_openid, " +
                "wx_openid, " +
                "real_name, " +
                "age, " +
                "phone, " +
                "gender, " +
                "description, " +
                "register_time, " +
                "login_time, " +
                "pic, " +
                "look, " +
                "is_secret, " +
                "create_time, " +
                "create_by, " +
                "del_flag  " +
                "FROM " +
                "user  " +
                "WHERE " +
                "username = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), name);
    }

    public User getUserByEmail(String email) {
        String sql="SELECT " +
                "id, " +
                "dept_id, " +
                "username, " +
                "password, " +
                "email, " +
                "qq_openid, " +
                "wx_openid, " +
                "real_name, " +
                "age, " +
                "phone, " +
                "gender, " +
                "description, " +
                "register_time, " +
                "login_time, " +
                "pic, " +
                "look, " +
                "is_secret, " +
                "create_time, " +
                "create_by, " +
                "del_flag  " +
                "FROM " +
                "user  " +
                "WHERE " +
                "email = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), email);
    }

    public void addUser(User user) {
        String sql = "insert into user(id,dept_id, username, password, email, qq_openid, wx_openid, real_name, age, phone, gender, description , register_time, login_time, pic, look, is_secret, create_time, create_by) values (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql,
                user.getDeptId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getQqOpenid(),
                user.getWxOpenid(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getGender(),
                user.getDescription(),
                user.getRegisterTime(),
                user.getLoginTime(),
                user.getPic(),
                user.getLook(),
                user.getIsSecret(),
                user.getCreateTime(),
                user.getCreateBy()
        );
    }

    public User getUserById(Integer id) {
        String sql="select  " +
                "id, " +
                "dept_id, " +
                "username, " +
                "password, " +
                "email, " +
                "qq_openid, " +
                "wx_openid, " +
                "real_name, " +
                "age, " +
                "phone, " +
                "gender, " +
                "description, " +
                "register_time, " +
                "login_time, " +
                "pic, " +
                "look, " +
                "is_secret, " +
                "create_time, " +
                "create_by, " +
                "del_flag " +
                "from user where id=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), id);
    }

    public void updatePic(Integer id, String fileName) {
        String sql = "update user set pic=? where id=?";
        template.update(sql, fileName, id);
    }

    public void updateUser(User user) {
        String sql = "update user set password=?,real_name=?,age=?,phone=?,gender=?,description=?,is_secret=?,dept_id=?,login_time=? where id=?";
        template.update(sql,
                user.getPassword(),
                user.getRealName(),
                user.getAge(),
                user.getPhone(),
                user.getGender(),
                user.getDescription(),
                user.getIsSecret(),
                user.getDeptId(),
                user.getLoginTime(),
                user.getId()
        );
    }

    public void updatePassword(String password,String email) {
        String sql = "update user set password=? where email=?";
        template.update(sql, password, email);
    }

    public List<User> listForExcel(User user) {
        String sql = "SELECT " +
                "u.username username, " +
                "u.real_name realName, " +
                "u.age age, " +
                "u.gender gender, " +
                "u.create_time createTime, " +
                "su.username createName  " +
                "FROM " +
                "user u " +
                "left join user su on su.id=u.create_by " +
                "where u.username like ? ";
        return template.query(sql, new BeanPropertyRowMapper<>(User.class), "%" + user.getUsername() + "%");
    }
}
