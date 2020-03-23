package com.wangt.service;

import com.wangt.dao.DeptDao;
import com.wangt.entity.Dept;
import com.wangt.entity.Page;
import com.wangt.utils.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/19 11:25
 */
public class DeptService {
    private DeptDao deptDao=new DeptDao();
    /*
     * @description 用户分页
     * @author wangt
     * @date 2020/3/19 11:27
     * @param [dept, page]
     * @return java.util.List<com.wangt.entity.Dept>
     */
    public List<Dept> list(Dept dept, Page page){
        return deptDao.list(dept,page);
    }
    /*
     * @description 分页总数据量
     * @author wangt
     * @date 2020/3/19 11:58
     * @param [dept]
     * @return java.lang.Integer
     */
    public Integer count(Dept dept){
        return deptDao.count(dept);
    }

    public Dept getDeptByName(String name) {
        Dept dept=null;
        if(StringUtils.isBlank(name)){
            return dept;
        }
        try {
            dept=deptDao.getDeptByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dept;
    }

    public void addDept(Dept dept) {
        dept.setCreateTime(DateUtil.getDateStr());
        deptDao.addDept(dept);
    }

    public void delDeptById(Integer id) {
        deptDao.delDeptById(id);
    }

    public void updateDeptByName(Dept dept) {
        deptDao.updateDeptByName(dept);
    }

    public List<Dept> listAll() {
        return deptDao.listAll();
    }
}
