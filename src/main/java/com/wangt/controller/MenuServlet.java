package com.wangt.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wangt.entity.Menu;
import com.wangt.service.MenuService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author wangt
 * @description
 * @date 2020/3/19 23:33
 */
@WebServlet("/menu/*")
public class MenuServlet extends BaseServlet {
    private MenuService menuService=new MenuService();

    protected void menu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取所有菜单
        List<Menu> list=menuService.listAll();
        //菜单分类
        //一级菜单
        List<Menu> parent=new ArrayList<>();
        //二级菜单
        List<Menu> son=new ArrayList<>();
        //第一种方式
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType() == 0) {
                parent.add(list.get(i));
            }
            if (list.get(i).getType() == 1) {
                son.add(list.get(i));
            }
        }
        //第二种方式
//        parent=list.stream().filter(n->{
//            return n.getType()==0;
//        }).collect(Collectors.toList());
//        son = list.stream().filter(n -> {
//            return n.getType() == 1;
//        }).collect(Collectors.toList());

        Map<String,List<Menu>> map=new HashMap<String, List<Menu>>();
        map.put("parent", parent);
        map.put("son", son);

        //转换为json
        //第一种方式 阿里的Fastjson
//        String mapJsonStr = JSON.toJSONString(map);
        //第二种方式 Jackson
        String mapJsonStr=new ObjectMapper().writeValueAsString(map);

        PrintWriter out = response.getWriter();
        out.write(mapJsonStr);
    }
}
