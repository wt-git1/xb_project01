package com.wangt.controller;

import com.wangt.constants.SysConstant;
import com.wangt.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangt
 * @description
 * @date 2020/3/18 10:10
 */
public class BaseServlet extends HttpServlet {
    //登录信息
    public User loginUser = new User();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        loginUser = (User) session.getAttribute(SysConstant.SESSION_LOGIN_USER);
        String uri=request.getRequestURI();
        String[] arrUri=uri.split("/");
        String methodName=arrUri[arrUri.length-1];
        Class clazz=this.getClass();
        try {
            Method method=clazz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.setAccessible(true);
            method.invoke(this,request,response);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
