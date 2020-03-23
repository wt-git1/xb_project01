package com.wangt.service;

import com.wangt.dao.UserDao;
import com.wangt.entity.Page;
import com.wangt.entity.User;
import com.wangt.utils.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/18 19:48
 */
public class UserService {
    private UserDao userDao=new UserDao();
    /*
     * @description 根据特定用户信息，获取包含分页的用户信息
     * @author wangt
     * @date 2020/3/18 20:37
     * @param [user, page]
     * @return java.util.List<com.wangt.entity.User>
     */
    public List<User> list(User user, Page page){
        return  userDao.list(user,page);
    }

    /*
     * @description 根据用户查询条件获取查询数目
     * @author wangt
     * @date 2020/3/18 21:01
     * @param []
     * @return java.util.List<com.wangt.entity.User>
     */
    public Integer count(User user) {
        return userDao.count(user);
    }

    /*
     * @description 通过用户名称，返回用户信息
     * @author wangt
     * @date 2020/3/18 22：34
     * @param [name]
     * @return com.wangt.entity.User
     */
    public User getUserByName(String name) {
        User user=null;
        if(StringUtils.isBlank(name)){
            return user;
        }
        try {
            user=userDao.getUserByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    /*
     * @description 通过用户邮箱，返回用户信息
     * @author wangt
     * @date 2020/3/18 22：34
     * @param [email]
     * @return com.wangt.entity.User
     */
    public User getUserByEmail(String email) {
        User user=null;
        if(StringUtils.isBlank(email)){
            return user;
        }
        try {
            user=userDao.getUserByEmail(email);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    /*
     * @description 注册用户
     * @author wangt
     * @date 2020/3/18 22：35
     * @param [user]
     * @return void
     */
    public void addUser(User user) {
        user.setRegisterTime(DateUtil.getDateStr());
        user.setCreateTime(DateUtil.getDateStr());
        user.setCreateBy(null);
        userDao.addUser(user);
    }

    public User getUserById(Integer id) {
        return userDao.getUserById(id);
    }

    public void updatePic(Integer id, String fileName) {
        userDao.updatePic(id,fileName);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void updatePassword(String password,String email) {
        userDao.updatePassword(password,email);
    }
}
