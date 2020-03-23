package com.wangt.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.wangt.constants.SysConstant;
import com.wangt.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author wangt
 * @description
 * @date 2020/3/17 22:30
 */
@WebFilter("/*")
public class SysFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //编码问题
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        HttpSession session=request.getSession();
        String uri=request.getRequestURI();
        //不需要拦截的请求
        if(uri.endsWith("/index.jsp")){
            Cookie[] cookies=request.getCookies();
            if(cookies!=null){
                for (int i=0;i<cookies.length;i++){
                    if(SysConstant.COOKIE_LOGIN_USER.equals(cookies[i].getName())){//如果cookie用户存在
                        //添加数据到session
                        session.setAttribute(SysConstant.SESSION_LOGIN_USER,
                                JSON.parseObject(URLDecoder.decode(cookies[i].getValue()),new TypeReference<User>(){}));
                        //直接跳转主界面
                        request.getRequestDispatcher("/html/common/home.jsp").forward(request,response);
                    }
                }
            }
            //如果没有cookie，跳转登录界面
            filterChain.doFilter(request, response);
            return;
        }else if(uri.endsWith("/register.jsp")||
                uri.endsWith("/login")||
                uri.endsWith("/register")||
                uri.endsWith("/checkUserName")||
                uri.endsWith("/checkEmail")||
                uri.endsWith("/getPic")||
                uri.endsWith("/weChatLogin")||
                uri.endsWith("/wxLoginCallBack")||
                uri.endsWith("/sendEmail")||
                uri.endsWith("/checkVerification")||
                uri.endsWith("/updatePassword")||
                uri.endsWith("/qqLogin")||
                uri.endsWith("/qq_login")||
                uri.endsWith("/update.jsp")){
            filterChain.doFilter(request, response);
            return;
        }
        //判断session中是否有登录信息
        Object obj=session.getAttribute(SysConstant.SESSION_LOGIN_USER);
        if(obj==null){
            //没有信息，跳转登录界面
            response.sendRedirect("/index.jsp");
            return;
        }
        request.setAttribute("loginUser", (User) obj);
        filterChain.doFilter(request,response);
    }
}
