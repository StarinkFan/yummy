package com.springboot.yummy.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

public class CodeUtil {
    static Map<String, String> codes = new HashMap<>();

    private static String createNewCode(){
        String result="";
        String[] nums = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for(int i=0;i<6;i++){
            result=result+nums[(int)(Math.random()*10)];
        }
        return result;
    }

    public static String getNewCode(String email){
        String newCode=createNewCode();
        codes.put(email, newCode);
        Timer timer = new Timer();
        timer.schedule(new TimeTask1(email), 120 * 1000);
        return newCode;
    }

    public static boolean checkCode(String email, String code){
        return codes.containsKey(email)&&codes.get(email).equals(code);
    }
}
