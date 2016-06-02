package cn.it.sales.util;

import android.util.Log;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/5/24.
 */
public class DateTimeUtil {
    /**
     * 获取系统时间减去八个小时即为格林威治时间
     * @return
     */
    public static String getSystemtDateTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // Date curDate = new Date(System.currentTimeMillis()-8*3600*1000);//获取当前时间减去（GET+8）即八个小时
        Date curDate = new Date(System.currentTimeMillis());
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String dateTimeString = formatter.format(curDate);
        Log.d("ret", "dateTimeString: " + dateTimeString);
        return dateTimeString;
    }
    /**
     * 获取系统时间转换为数据库格林威治时间  "即GMT+0"
     * @return
     */
    public static String getSystemtDateTimeToSQL3GMT0(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+0"));// 格林威治时间
        String dateTimeString = formatter.format(curDate);
        String ret = "datetime('"+dateTimeString+"','localtime')";
        return ret;

    }
}
