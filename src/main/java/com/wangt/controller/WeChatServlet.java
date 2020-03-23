package com.wangt.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wangt.constants.SysConstant;
import com.wangt.entity.User;
import com.wangt.service.LoginService;
import com.wangt.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;
import java.util.UUID;

/**
 * @author wangt
 * @description
 * @date 2020/3/21
 */
@WebServlet("/weChat/*")
public class WeChatServlet extends BaseServlet{
    private LoginService loginService=new LoginService();
    private UserService userService=new UserService();
    /*
     * @description 跳转微信登录界面
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void weChatLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加载config.properties配置文件
        Properties properties=new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        String appId=properties.getProperty("wx.AppID");
        String redirectUri = properties.getProperty("wx.redirect_uri");
        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code" +
                "&appid=" + appId +
                "&redirect_uri=" + URLEncoder.encode(redirectUri) +
                "&scope=snsapi_login";
        // 重定向到微信登录指定的地址进行微信登录授权,授权成功后返回code
        response.sendRedirect(url);
    }
    /*
     * @description 处理微信回调
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void wxLoginCallBack(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //加载config.properties配置文件
        Properties properties=new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));
        String appId=properties.getProperty("wx.AppID");
        String appSecret = properties.getProperty("wx.AppSecret");
        String code = request.getParameter("code");

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                "appid=" +appId+
                "&secret=" +appSecret+
                "&code=" +code+
                "&grant_type=authorization_code";
        // 通过code获取access_token、openid等数据
        JSONObject info= loginService.getJsonObject(url);
        url = "https://api.weixin.qq.com/sns/userinfo?" +
                "access_token=" + info.getString("access_token") +
                "&openid=" + info.getString("openid");
        //通过access_token、openid获取用户信息(名称，性别。。)
        JSONObject userInfo =loginService.getJsonObject(url);
        // 判断是否有微信登录过
        User user = loginService.findByWeChatOpenid(info.getString("openid"));
        //第一次使用，将用户数据保存到数库
        if(user==null){
            //新增用户
            user=new User();
            //用户头像
            user.setPic(userInfo.getString("headimgurl"));
            //用户名
            user.setRealName(userInfo.getString("nickname"));
            //用户性别
            user.setGender(userInfo.getString("sex"));
            // 随机用户名
            user.setUsername(UUID.randomUUID().toString().substring(21));

            user.setWxOpenid(info.getString("openid"));
            // 注册一个新的用户
            userService.addUser(user);
            //查询用户信息
            user = loginService.findByWeChatOpenid(info.getString("openid"));
        }
        HttpSession session = request.getSession();
        session.setAttribute(SysConstant.SESSION_LOGIN_USER,user);
        //跳转到主界面
        response.sendRedirect("/html/common/home.jsp");
    }
}
