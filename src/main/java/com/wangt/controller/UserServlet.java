package com.wangt.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.wangt.entity.Dept;
import com.wangt.entity.Page;
import com.wangt.entity.User;
import com.wangt.service.DeptService;
import com.wangt.service.UserService;
import com.wangt.utils.EmailUtil;
import com.wangt.utils.MdUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author wangt
 * @description
 * @date 2020/3/18 19:47
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{
    private UserService userService=new UserService();
    private DeptService deptService=new DeptService();
    private EmailUtil emailUtil=new EmailUtil();
    /*
     * @description 用户数据展示（查询+分页）
     * @author wangt
     * @date 2020/3/19 23:14
     * @param [request, response]
     * @return void
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数信息
        String username= request.getParameter("username");
        username=username==null ?"":username;
        String pageNoStr=request.getParameter("pageNo");
        Integer pageNo=pageNoStr==null ? 1:Integer.valueOf(pageNoStr);
        //查询条件
        User user = new User();
        user.setUsername(username);
        //分页条件
        Integer rowCount=userService.count(user);
        Page page=new Page();
        page.setPageNo(pageNo);
        page.setRowCount(rowCount);

        //返回数据
        List<User> list=userService.list(user,page);
        request.setAttribute("list",list);
        request.setAttribute("user",user);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/html/user/list.jsp").forward(request,response);
    }
    /*
     * @description 根据姓名查询用户是否存在
     * @author wangt
     * @date 2020/3/19 23:15
     * @param [request, response]
     * @return void
     */
    protected void checkUserName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String name = request.getParameter("name");
        //返回信息
        User user=userService.getUserByName(name);
        PrintWriter out =response.getWriter();
        if(user==null){
            out.write("200");
        }else {
            out.write("500");
        }
    }
    /*
     * @description 根据邮箱查询用户是否存在
     * @author wangt
     * @date 2020/3/19 23:17
     * @param [request, response] 
     * @return void
     */
    protected void checkEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String email = request.getParameter("email");
        //返回信息
        User user=userService.getUserByEmail(email);
        PrintWriter out =response.getWriter();
        if(user==null){
            out.write("200");
        }else {
            out.write("500");
        }
    }
    /*
     * @description 用户注册
     * @author wangt
     * @date 2020/3/19 23:17
     * @param [request, response] 
     * @return void
     */
    protected void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //封装数据
        User user=new User();
        user.setUsername(username);
        user.setPassword(MdUtil.md5(password));
        user.setEmail(email);
        //添加用户
        userService.addUser(user);
        //注册成功，跳转登录界面
        response.sendRedirect("/index.jsp");
    }
    /*
     * @description 查询用户详细信息
     * @author wangt
     * @date 2020/3/19 23:17
     * @param [request, response] 
     * @return void
     */
    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String id = request.getParameter("id");

        User user=userService.getUserById(Integer.valueOf(id));
        List<Dept> dept=deptService.listAll();
        //返回数据
        request.setAttribute("user",user);
        request.setAttribute("dept",dept);
        request.getRequestDispatcher("/html/user/detail.jsp").forward(request,response);
    }
    /*
     * @description 更新用户信息
     * @author wangt
     * @date 2020/3/19 23:18
     * @param [request, response]
     * @return void
     */
    protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        //获取参数
        Map<String,String[]> map=request.getParameterMap();
        User user =new User();
        BeanUtils.populate(user,map);
        User user2=userService.getUserById(user.getId());
        //复制旧的属性过来，忽略null属性，有值的以新的为主，null的则以旧为主
        //cn.hutool.core.bean.BeanUtil;
        cn.hutool.core.bean.BeanUtil.copyProperties(user,user2,true, CopyOptions.create().create().setIgnoreNullValue(true).setIgnoreError(true));
        userService.updateUser(user2);
        response.sendRedirect("/user/list");
    }
    /*
     * @description发送邮箱验证码
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void sendEmail(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
        //获取参数
        String email=request.getParameter("email");
        //判断该邮箱是否注册
        User user = userService.getUserByEmail(email);
        PrintWriter out =response.getWriter();
        if(user==null){
            out.write("500");
        }else {
            //发送邮箱验证码
            String emailCode= emailUtil.sendEmail(email);
            request.setAttribute("emailCode",emailCode);
            out.write("200");
        }
    }
    /*
     * @description 检查验证码是否正确
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void checkVerification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取参数
        String verification=request.getParameter("verification");
        PrintWriter out =response.getWriter();
        if(StringUtils.isEmpty(emailUtil.getCode())||!emailUtil.getCode().equals(verification)){
            out.write("500");
        }else {
            out.write("200");
        }
    }

    /*
     * @description 重置密码
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void updatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //获取参数
        String password=request.getParameter("password");
        userService.updatePassword(MdUtil.md5(password),emailUtil.getEmail());
        PrintWriter out =response.getWriter();
        out.write("200");
    }
}
