package com.wangt.service;

import com.alibaba.fastjson.JSONObject;
import com.wangt.dao.LoginDao;
import com.wangt.entity.User;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.dao.EmptyResultDataAccessException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangt
 * @description 登录
 * @date 2020/3/17 22:30
 */
public class LoginService {
    private LoginDao loginDao = new LoginDao();
    /*
     * @description 通过用户名，密码获取用户信息
     * @author wangt
     * @date 2020/3/18 22：33
     * @param [user]
     * @return com.wangt.entity.User
     */
    public User checkLogin(User user) {
        return loginDao.checkLogin(user);
    }

    public JSONObject getJsonObject(String url) {
        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            // 获取响应数据(json)
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));
                return JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }
    /*
     * @description 把参数变为Map格式
     * @author wangt
     * @date 2020/3/22
     * @param [url]
     * @return java.util.Map<java.lang.String,java.lang.String>
     */
    public Map<String,String> getMap(String url) {
        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            // 获取响应数据(json)
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));
                String[] arr= result.split("&");
                Map<String,String> map=new HashMap<>();
                for(int i=0;i<arr.length;i++){
                    String key=arr[i].substring(0,arr[i].indexOf("="));
                    String value=arr[i].substring(arr[i].indexOf("=")+1,arr[i].length());
                    map.put(key,value);
                }
                return map;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /*
     * @description qq的JsonObject
     * @author wangt
     * @date 2020/3/22
     * @param
     * @return
     */
    public JSONObject getQqJsonObject(String url) {
        try {
            // 创建一个http Client请求
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = client.execute(httpGet);
            // 获取响应数据(json)
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, Charset.forName("UTF8"));
                result=result.substring(9,result.length()-4);
                return JSONObject.parseObject(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public User findByWeChatOpenid(String openid) {
        try {
            return loginDao.findByWeChatOpenid(openid);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    public User findByQqOpenid(String openid) {
        try {
            return loginDao.findByQqOpenid(openid);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }
}
