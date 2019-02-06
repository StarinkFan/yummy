package com.springboot.yummy.util;

import java.util.TimerTask;

class TimeTask1 extends TimerTask {
    private String email;

    TimeTask1(String email){
        this.email=email;
    }

    public void run() {
        CodeUtil.codes.remove(email);
        System.out.println(email+"验证码失效");
    }
}
