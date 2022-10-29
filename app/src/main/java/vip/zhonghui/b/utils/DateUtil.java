package vip.zhonghui.b.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with Android Studio.
 *
 * @author: 炸薯条
 * Date: 2022/10/7
 * Time: 3:11
 * Description: No Description
 */
public class DateUtil {

    public static String getDate(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }

    public static String getWeek(Calendar calendar) {
        Date date = calendar.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("EEEE");
        return sf.format(date);
    }

    public static String getEveyDate(String date) {
        List<String> list = new ArrayList<>();
        String[] split = date.split("-");
        for (String s : split) {
            list.add(s);
        }
        return list.get(0) + "年" + list.get(1) + "月" + list.get(2) + "日";
    }

    public static Integer getTime(String date) {
        SimpleDateFormat sf = new SimpleDateFormat("hh:mm");
        try {
            Date time = sf.parse(date);
            return (int) time.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
