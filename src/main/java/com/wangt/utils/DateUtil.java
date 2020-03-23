package com.wangt.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangt
 * @description 日期格式工具类
 * @date 2020/3/18 19:48
 */
public class DateUtil {
    public static String getDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
