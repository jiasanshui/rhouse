package edu.rent.house.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BaseUtil {

    public static String getNum(){
        String prefix = "xxx";
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
}
