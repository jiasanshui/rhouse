package edu.rent.house.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BaseUtil {

    public static String getNum(){
        String prefix = "rh";
        String date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String suffix = String.valueOf((int)(Math.random() * 9 + 1) * 1000);
        return prefix+date+suffix;
    }

    /**
     * 返回6位验证码
     * @return
     */
    public static String getSmsCode(){
        Random random = new Random();
        String result="";
        for(int i=0;i<6;i++){
            result+=random.nextInt(10);
        }
        return result;
    }

    public static String dateFormatStr(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        String newDate = null;
        try {
            date = sdf.parse(dateStr);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            newDate = format.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }
}
