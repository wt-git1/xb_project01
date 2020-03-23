package com.wangt.controller;

import com.wangt.entity.Meeting;
import com.wangt.entity.Page;
import com.wangt.service.MeetService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/22
 */
@WebServlet("/meet/*")
public class MeetServlet extends BaseServlet{
    private MeetService meetService=new MeetService();
    /*
     * @description 查询会议信息（查询+分页）
     * @author wangt
     * @date 2020/3/22
     * @param [request, response]
     * @return void
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取参数
        String title = request.getParameter("title");
        title=title==null ? "":title;
        String status = request.getParameter("status");
        Integer meetStatus=null;
        if(status!=null&&!status.equals("-1")&&!status.equals("")){
            meetStatus=Integer.valueOf(status);
        }
        String pageNoStr=request.getParameter("pageNo");
        Integer pageNo=pageNoStr==null ?1:Integer.valueOf(pageNoStr);

        Meeting meeting=new Meeting();
        meeting.setTitle(title);
        meeting.setStatus(meetStatus);
        Integer rowCount=meetService.count(meeting);
        Page page =new Page();
        page.setPageNo(pageNo);
        page.setRowCount(rowCount);
        //设置每页长度
        page.setPageSize(2);

        List<Meeting> list=meetService.list(meeting,page);
        //返回数据
        request.setAttribute("meet",meeting);
        System.out.println("status"+meeting.getStatus());
        request.setAttribute("page",page);
        request.setAttribute("list",list);
        request.getRequestDispatcher("/html/meet/list.jsp").forward(request,response);
    }
}
