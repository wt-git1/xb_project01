package com.wangt.controller;

import com.alibaba.fastjson.JSON;
import com.wangt.constants.SysConstant;
import com.wangt.entity.User;
import com.wangt.service.LoginService;
import com.wangt.utils.MdUtil;
import org.apache.commons.lang.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;

/**
 * @author wangt
 * @description
 * @date 2020/3/17 22:28
 */
@WebServlet("/login/*")
public class LoginServlet extends BaseServlet {
    private LoginService loginService=new LoginService();
    /*
     * @description 用户登录
     * @author wangt
     * @date 2020/3/19 23:18
     * @param [request, response]
     * @return void
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取账号，密码，验证码
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        String picCode=request.getParameter("picCode");
        String saveTime=request.getParameter("saveTime");
        //获取验证码
        HttpSession session=request.getSession();
        Object copy=session.getAttribute(SysConstant.SESSION_PIC_COPE);
        //判断验证码是否为空
//        if(copy==null){
//            //为空，跳转登录界面
//            response.sendRedirect("/index.jsp");
//            return;
//        }
//        //判断验证码是否正确
//        if(!copy.toString().equals(picCode)){
//            //错误，跳转登录界面
//            response.sendRedirect("/index.jsp");
//            return;
//        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(MdUtil.md5(password));
        User result=null;
        try {
            result=loginService.checkLogin(user);
        }catch (Exception e){
            response.sendRedirect("/index.jsp");
            return;
        }
        //判断用户是否存在
        if(result==null){
            //为空，跳转登录界面
            response.sendRedirect("/index.jsp");
            return;
        }
        //判断是否勾选记住时间
        if(StringUtils.isNotEmpty(saveTime) &&"1".equals(saveTime)){
            //将用户信息存进Cookie
            Cookie cookie=new Cookie(SysConstant.COOKIE_LOGIN_USER, URLEncoder.encode(JSON.toJSONString(result)));
            //有效值一天
            cookie.setMaxAge(1*24*60*60);
            //任何请求都能获取cookie
            cookie.setPath("/");
            response.addCookie(cookie);

        }
        //把用户信息放到session中
        session.setAttribute(SysConstant.SESSION_LOGIN_USER,result);
        request.setAttribute("loginUser", result);
        request.getRequestDispatcher("/html/common/home.jsp").forward(request,response);
    }
    /*
     * @description 退出系统
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //清除session
        HttpSession session=request.getSession();
        //清除session3种方法
        session.removeAttribute(SysConstant.SESSION_LOGIN_USER);
        session.setMaxInactiveInterval(0);
        session.invalidate();
        //清除loginUser
        Cookie[] cookies=request.getCookies();
        if(cookies!=null){
            for (int i=0;i<cookies.length;i++){
                if(SysConstant.COOKIE_LOGIN_USER.equals(cookies[i].getName())){
                    cookies[i].setPath("/");
                    cookies[i].setMaxAge(0);
                    response.addCookie(cookies[i]);
                }
            }
        }
        response.sendRedirect("/index.jsp");
    }
    /*
     * @description 跳转qq登录界面
     * @author wangt
     * @date 2020/3/22
     * @param [request, response]
     * @return void
     */
    protected void qqLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加载config.properties配置文件
        Properties properties=new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        String appId=properties.getProperty("qq.AppID");
        String redirectUri= properties.getProperty("qq.redirect_uri");
        String code = request.getParameter("code");
        //获取Authorization Code
        String url="https://graph.qq.com/oauth2.0/authorize?" +
                "response_type=code" +
                "&client_id="+appId+
                "&redirect_uri="+redirectUri+
                "&state=1";
        response.sendRedirect(url);
    }
}
