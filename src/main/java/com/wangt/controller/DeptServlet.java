package com.wangt.controller;

import com.wangt.dao.BaseDao;
import com.wangt.entity.Dept;
import com.wangt.entity.Page;
import com.wangt.service.DeptService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/19 11:27
 */
@WebServlet("/dept/*")
public class DeptServlet extends BaseServlet {
    private DeptService deptService=new DeptService();
    /*
     * @description 查询部门信息（查询+分页）
     * @author wangt
     * @date 2020/3/19 23:19
     * @param [request, response]
     * @return void
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String name = request.getParameter("name");
        name=name==null ? "":name;
        String pageNoStr=request.getParameter("pageNo");
        Integer pageNo=pageNoStr==null ?1:Integer.valueOf(pageNoStr);

        Dept dept=new Dept();
        dept.setName(name);
        Integer rowCount=deptService.count(dept);
        Page page =new Page();
        page.setPageNo(pageNo);
        page.setRowCount(rowCount);

        List<Dept> list=deptService.list(dept,page);
        //返回数据
        request.setAttribute("dept",dept);
        request.setAttribute("page",page);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/html/dept/list.jsp").forward(request,response);
    }
    /*
     * @description 根据名称判断部门是存在
     * @author wangt
     * @date 2020/3/19 23:19
     * @param [request, response]
     * @return void
     */
    protected void checkName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String name = request.getParameter("name");
        //返回信息
        Dept user=deptService.getDeptByName(name);
        PrintWriter out =response.getWriter();
        if(user==null){
            out.write("200");
        }else {
            out.write("500");
        }
    }
    /*
     * @description 新增部门
     * @author wangt
     * @date 2020/3/19 23:20
     * @param [request, response]
     * @return void
     */
    protected void addDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String name = request.getParameter("name");

        Dept dept=new Dept();
        dept.setName(name);
        //添加数据
        deptService.addDept(dept);
        //重定向到部门界面
        response.sendRedirect("/dept/list");
    }
    /*
     * @description 删除部门
     * @author wangt
     * @date 2020/3/19 23:20
     * @param [request, response]
     * @return void
     */
    protected void delDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String id = request.getParameter("id");
        if(!(id==null ||id=="")){
            //删除部门
            deptService.delDeptById(Integer.valueOf(id));
        }
        //重定向到部门界面
        response.sendRedirect("/dept/list");
    }
    /*
     * @description 回显部门信息
     * @author wangt
     * @date 2020/3/19 23:21
     * @param [request, response]
     * @return void
     */
    protected void toUpdateDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        Dept dept=new Dept();
        dept.setId(Integer.valueOf(id));
        dept.setName(name);
        //数据
        request.setAttribute("dept",dept);
        //转发到修改部门界面
        request.getRequestDispatcher("/html/dept/update.jsp").forward(request,response);
    }
    /*
     * @description 更新部门信息
     * @author wangt
     * @date 2020/3/19 23:21
     * @param [request, response]
     * @return void
     */
    protected void updateDept(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        Dept dept=new Dept();
        dept.setId(Integer.valueOf(id));
        dept.setName(name);
        //修改部门名称
        deptService.updateDeptByName(dept);
        //重定向到部门界面
        response.sendRedirect("/dept/list");
    }
}
