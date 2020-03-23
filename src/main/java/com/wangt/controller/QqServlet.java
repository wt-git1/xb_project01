package com.wangt.controller;

import com.alibaba.fastjson.JSONObject;
import com.wangt.constants.SysConstant;
import com.wangt.entity.User;
import com.wangt.service.LoginService;
import com.wangt.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

/**
 * @author wangt
 * @description
 * @date 2020/3/22 22:03
 */
@WebServlet("/qq_login")
public class QqServlet  extends HttpServlet {
    private LoginService loginService=new LoginService();
    private UserService userService=new UserService();
    /*
     * @description 出来qq回调
     * @author wangt
     * @date 2020/3/23
     * @param [request, response]
     * @return void
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加载config.properties配置文件
        Properties properties=new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        String appId=properties.getProperty("qq.AppID");
        String appKey= properties.getProperty("qq.AppKey");
        String redirectUri= properties.getProperty("qq.redirect_uri");
        String code = request.getParameter("code");
        //获取Authorization Code
        String url="https://graph.qq.com/oauth2.0/token?grant_type=authorization_code" +
                "&client_id="+appId+
                "&client_secret="+appKey+
                "&code="+code+
                "&redirect_uri="+redirectUri;
        Map<String,String> info =loginService.getMap(url);

        //使用Access Token来获取用户的OpenID
        url="https://graph.qq.com/oauth2.0/me?access_token="+info.get("access_token");
        JSONObject userInfo = loginService.getQqJsonObject(url);

        //使用Access Token以及OpenID来访问和修改用户数据
        url="https://graph.qq.com/user/get_user_info?" +
                "access_token=" +info.get("access_token")+
                "&oauth_consumer_key=" +appId+
                "&openid="+userInfo.getString("openid");
        JSONObject userContent = loginService.getJsonObject(url);
        System.out.println("userContent:"+userContent);
        //判断该用户是否第一次使用qq登录
        User user = loginService.findByQqOpenid(userInfo.getString("openid"));
        if (user == null) {//第一次登录
            user = new User();
            // 头像
            user.setPic(userContent.getString("headimgurl"));
            // 性别
            user.setGender(userContent.getString("gender"));
            // \昵称
            user.setRealName(userContent.getString("nickname"));
            // 随机用户名(11位随机字符串)
            user.setUsername(UUID.randomUUID().toString().substring(36 - 15));
            user.setQqOpenid(userInfo.getString("openid"));
            // 注册一个新的用户
            userService.addUser(user);
            user = loginService.findByQqOpenid(userInfo.getString("openid"));
        }
        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_LOGIN_USER, user);
        response.sendRedirect("/html/common/home.jsp");
    }


}
