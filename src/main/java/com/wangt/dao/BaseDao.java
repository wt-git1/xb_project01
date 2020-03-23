package com.wangt.dao;

import com.wangt.utils.DBUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author wangt
 * @description
 * @date 2020/3/17 22:38
 */
public class BaseDao{
    public JdbcTemplate template=new JdbcTemplate(DBUtil.getDataSource());

}
