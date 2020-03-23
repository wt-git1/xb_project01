package com.wangt.service;

import com.wangt.dao.MenuDao;
import com.wangt.entity.Menu;

import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/19 23:32
 */
public class MenuService {
    private MenuDao menuDao = new MenuDao();
    public List<Menu> listAll() {
        return menuDao.listAll();
    }
}
