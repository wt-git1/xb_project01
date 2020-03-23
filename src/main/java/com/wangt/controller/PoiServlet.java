package com.wangt.controller;

import com.wangt.entity.User;
import com.wangt.service.PoiService;
import com.wangt.service.UserService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/21
 */
@WebServlet("/poi/*")
public class PoiServlet extends BaseServlet {
    private PoiService poiService=new PoiService();
    private UserService userService=new UserService();
    /*
     * @description 导出用户excel
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void userExportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        username = username == null ? "" : username;
        User user = new User();
        user.setUsername(username);
        Workbook wb = poiService.userExportExcel(user);
        //将excel实例以流的形式写回浏览器
        //设置表格名
        downloadExcel(response, wb, "用户列表Excel.xls");
    }
    /*
     * @description excel下载格式
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    public void downloadExcel(HttpServletResponse response, Workbook wb, String fileName) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
        response.setContentType("application/ynd.ms-excel;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        wb.write(out);
        out.flush();
        out.close();
    }
    /*
     * @description 下载用户列表模板
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void userDownLoadTemplate(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Workbook wb = poiService.userDownLoadTemplate();
        downloadExcel(response, wb, "用户列表模板Excel.xls");
    }
    /*
     * @description 导入Excel到数据库
     * @author wangt
     * @date 2020/3/21
     * @param [request, response]
     * @return void
     */
    protected void userImportExcel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, Exception {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        if (!ServletFileUpload.isMultipartContent(request)) {
            response.getWriter().println("表单的enctype属性不是multipart/form-data类型");
        }
        //设置单个文件上传大小5M
        fileUpload.setFileSizeMax(5 * 1024 * 1024);
        //设置总上传文件大小20M
        fileUpload.setSizeMax(20 * 1024 * 1024);
        try {
            List<FileItem> parseRequest = fileUpload.parseRequest(request);
            for (FileItem fileItem : parseRequest) {
                if (!fileItem.isFormField()) {
                    String fileName = fileItem.getName();
                    String[] arr = fileName.split("\\.");
                    String suffix = arr[arr.length - 1];
                    //根据上传的excel文件构造workbook实例（xls与xlsx）
                    InputStream fileStream = fileItem.getInputStream();
                    Workbook wb = poiService.getWorkbook(fileStream, suffix);
                    //读取上传上来的excel的数据存放到List<User>中
                    List<User> list = poiService.getExcelData(wb);
                    //插入到数据库
                    for (User user : list) {
                        userService.addUser(user);
                    }
                    fileStream.close();
                } else {
                    String fieldName = fileItem.getFieldName();
                    String fieldValue = fileItem.getString();
                    System.out.println(fieldName + ":" + fieldValue);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

    }
}
