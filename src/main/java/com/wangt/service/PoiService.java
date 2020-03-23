package com.wangt.service;

import com.wangt.dao.UserDao;
import com.wangt.entity.User;
import com.wangt.utils.DateUtil;
import com.wangt.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangt
 * @description
 * @date 2020/3/21
 */
public class PoiService {
    private UserDao userDao=new UserDao();
    /*
     * @description 把用户数据导出到Excel
     * @author wangt
     * @date 2020/3/22
     * @param [user]
     * @return org.apache.poi.ss.usermodel.Workbook
     */
    public Workbook userExportExcel(User user) {
        String[] headers =new String[]{"用户名", "真实姓名", "性别", "年龄", "创建时间", "创建人"};
        Workbook wb = new HSSFWorkbook();
        //sheet名称
        Sheet sheet = wb.createSheet("用户信息列表");
        //创建表头
        Row headerRow=sheet.createRow(0);
        for (int i=0;i<headers.length;i++){
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        List<User> list=userDao.listForExcel(user);
        Row row;
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(list.get(i).getUsername() == null ? "" : list.get(i).getUsername());
            row.createCell(1).setCellValue(list.get(i).getRealName() == null ? "" : list.get(i).getRealName());
            String gender = "";
            if (list.get(i).getGender() != null) {
                if (list.get(i).getGender().equals(1)) {
                    gender = "男";
                } else if (list.get(i).getGender().equals(0)) {
                    gender = "女";
                }
            }
            row.createCell(2).setCellValue(gender);
            String age = list.get(i).getAge() == null ? "" : list.get(i).getAge().toString();
            row.createCell(3).setCellValue(age);
            row.createCell(4).setCellValue(list.get(i).getCreateTime() == null ? "" : list.get(i).getCreateTime());
            row.createCell(5).setCellValue(list.get(i).getCreateName() == null ? "" : list.get(i).getCreateName());
        }
        return wb;
    }
    /*
     * @description 用户下载模板
     * @author wangt
     * @date 2020/3/21
     * @param []
     * @return org.apache.poi.ss.usermodel.Workbook
     */
    public Workbook userDownLoadTemplate() {
        String[] headers = new String[]{"用户名", "真实姓名", "性别", "年龄", "创建时间", "创建人"};
        Workbook wb = new HSSFWorkbook();
        //sheet名称
        Sheet sheet = wb.createSheet("用户信息列表模板");
        //创建表头
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        return wb;
    }
    /*
     * @description 根据后缀名区分来获取workbook实例
     * @author wangt
     * @date 2020/3/21
     * @param [fileStream, suffix]
     * @return org.apache.poi.ss.usermodel.Workbook
     */
    public Workbook getWorkbook(InputStream fileStream, String suffix) throws IOException {
        Workbook wk = null;
        //后缀名结尾是xls的是2003版本workBook，后缀名是xlsx结尾的是2007版本workBook
        if ("xls".equalsIgnoreCase(suffix)) {
            wk = new HSSFWorkbook(fileStream);
        } else if ("xlsx".equalsIgnoreCase(suffix)) {
            wk = new XSSFWorkbook(fileStream);
        }
        return wk;
    }

    public List<User> getExcelData(Workbook wb) throws Exception {
        List<User> list = new ArrayList<>();
        Row row;
        User user;
        int numSheet = wb.getNumberOfSheets();
        if (numSheet > 0) {
            for (int i = 0; i < numSheet; i++) {
                //获取每个sheet
                Sheet sheet = wb.getSheetAt(i);
                //获取总行数
                int numRow = sheet.getLastRowNum();
                if (numRow > 0) {
                    for (int j = 1; j <= numRow; j++) {
                        //跳过excel sheet表格头部
                        row = sheet.getRow(j);
                        user = new User();
                        user.setUsername(ExcelUtil.manageCell(row.getCell(0), null));
                        user.setRealName(ExcelUtil.manageCell(row.getCell(1), null));
                        String sex = ExcelUtil.manageCell(row.getCell(2), null);
                        user.setGender("男".equals(sex) ? "1" : "0");
                        user.setAge(Integer.valueOf(ExcelUtil.manageCell(row.getCell(3), null)));
                        user.setCreateTime(DateUtil.getDateStr());
                        user.setCreateBy(1);
                        list.add(user);
                    }
                }
            }
        }

        return list;
    }
}
