package com.wangt.dao;

import com.wangt.entity.Dept;
import com.wangt.entity.Page;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/19 10:55
 */
public class DeptDao extends BaseDao {
    public List<Dept> list(Dept dept, Page page){
        String sql="SELECT " +
                "d.id id, " +
                "d.name name, " +
                "d.create_time createTime, " +
                "d.create_by createBy, " +
                "d.del_flag delFlag, " +
                "count( u.id ) count " +
                "FROM " +
                "dept d " +
                "LEFT JOIN USER u ON d.id = u.dept_id  " +
                "WHERE " +
                "NAME LIKE ?  " +
                "GROUP BY " +
                "d.id  " +
                "LIMIT ?,?";
        return template.query(sql,new BeanPropertyRowMapper<>(Dept.class),"%"+dept.getName()+"%",page.getFirstResult(),page.getPageSize());
    }

    public Integer count(Dept dept){
        String sql="select count(id) from dept where name like ?" ;
        return template.queryForObject(sql,Integer.class,"%"+dept.getName()+"%");
    }

    public Dept getDeptByName(String name) {
        String sql ="SELECT " +
                "id, " +
                "name, " +
                "create_time, " +
                "create_by, " +
                "del_flag  " +
                "FROM " +
                "dept  " +
                "WHERE " +
                "name =?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<>(Dept.class),name);
    }

    public void addDept(Dept dept) {
        String sql="insert into dept(id,name,create_time,create_by,del_flag) " +
                "values(null,?,?,?,?)";
        template.update(sql,dept.getName(),dept.getCreateTime(),dept.getCreateBy(),dept.getDelFlag());
    }

    public void delDeptById(Integer id) {
        String sql="delete from dept where id=?";
        template.update(sql,id);
    }

    public void updateDeptByName(Dept dept) {
        String sql="update dept set name=? where id=?";
        template.update(sql,dept.getName(),dept.getId());
    }

    public List<Dept> listAll() {
        String sql ="SELECT " +
                "id, " +
                "name, " +
                "create_time, " +
                "create_by, " +
                "del_flag  " +
                "FROM " +
                "dept  ";
        return template.query(sql,new BeanPropertyRowMapper<>(Dept.class));
    }
}
