package com.xzzb.bloom.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HP on 2017/1/2.
 */

public class TimesUtil {
    public static String getYearStr(){
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        return year.format(new Date());
    }
    public static String getMonthStr(){
        SimpleDateFormat month = new SimpleDateFormat("MM");
        return month.format(new Date());
    }
    public static String getDayStr(){
        SimpleDateFormat day = new SimpleDateFormat("dd");
        return day.format(new Date());
    }
    public static int getYearInt(){
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        return Integer.parseInt(year.format(new Date()));
    }
    public static int getMonthInt(){
        SimpleDateFormat month = new SimpleDateFormat("MM");
        return Integer.parseInt(month.format(new Date()));
    }
    public static int getDayInt(){
        SimpleDateFormat day = new SimpleDateFormat("dd");
        return Integer.parseInt(day.format(new Date()));
    }
    public static String getHourStr(){
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        return hour.format(new Date());
    }
    public static String getMinuteStr(){
        SimpleDateFormat minute = new SimpleDateFormat("mm");
        return minute.format(new Date());
    }
    public static String getSecondStr(){
        SimpleDateFormat second = new SimpleDateFormat("ss");
        return second.format(new Date());
    }
    public static int getHourInt(){
        SimpleDateFormat hour = new SimpleDateFormat("HH");
        return Integer.parseInt(hour.format(new Date()));
    }
    public static int getMinuteInt(){
        SimpleDateFormat minute = new SimpleDateFormat("mm");
        return Integer.parseInt(minute.format(new Date()));
    }
    public static int getSecondInt(){
        SimpleDateFormat second = new SimpleDateFormat("ss");
        return Integer.parseInt(second.format(new Date()));
    }
    public static String getYMD(){
        SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd");
        return ymd.format(new Date());
    }
    public static String getHM(){
        SimpleDateFormat ymd = new SimpleDateFormat("HH:mm");
        return ymd.format(new Date());
    }
    public static String getHMS(){
        SimpleDateFormat ymd = new SimpleDateFormat("HH:mm:ss");
        return ymd.format(new Date());
    }
    public static String getTimes(){
        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return time.format(new Date());
    }
}
