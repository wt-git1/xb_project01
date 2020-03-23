package com.wangt.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @auth admin
 * @date 2020/3/17 15:42
 * @Description jdbcTemplate连接工具类
 */
public class DBUtil {

    private static DruidDataSource druidDataSource;

    public static void main(String[] args) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
        System.out.println("=======测试jdbcTemplate:" + jdbcTemplate);
    }

    static {
        Properties prop = new Properties();
        InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
        try {
            prop.load(in);
            druidDataSource = new DruidDataSource();
            druidDataSource.setUsername(prop.getProperty("jdbc.username"));
            druidDataSource.setPassword(prop.getProperty("jdbc.password"));
            druidDataSource.setUrl(prop.getProperty("jdbc.url"));
            druidDataSource.setDriverClassName(prop.getProperty("jdbc.driverClassName"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据源
     */
    public static DruidDataSource getDataSource() {
        return druidDataSource;
    }

}
