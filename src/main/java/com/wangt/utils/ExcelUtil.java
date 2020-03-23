package com.wangt.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 * excel工具类
 */
public class ExcelUtil {

    /**
     * 处理从excel读取到的单元格数据
     *
     * @param cell
     * @throws Exception
     */
    public static String manageCell(Cell cell, String dateFormat) throws Exception {
        DecimalFormat decimalFormatZero = new DecimalFormat();
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat decimalFormatNumeric = new DecimalFormat("0.00");

        String value = "";
        CellType cellType = cell.getCellTypeEnum();
        if (CellType.STRING.equals(cellType)) {
            value = cell.getStringCellValue();
        } else if (CellType.NUMERIC.equals(cellType)) {
            if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                value = decimalFormatZero.format(cell.getNumericCellValue());

            } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                value = sdf.format(cell.getDateCellValue());
            } else {
                value = decimalFormatNumeric.format(cell.getNumericCellValue());
            }
        } else if (CellType.BOOLEAN.equals(cellType)) {
            value = String.valueOf(cell.getBooleanCellValue());
        } else if (CellType.BLANK.equals(cellType)) {
            value = "";
        }
        return value;
    }


}