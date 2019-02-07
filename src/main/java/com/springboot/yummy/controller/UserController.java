package com.springboot.yummy.controller;

import com.springboot.yummy.service.UserService;
import com.springboot.yummy.util.CodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Transactional
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired  //自动装配
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Map<String,Object> login(@RequestBody Map<String, Object> requestMap){
        System.out.println("login");
        String email=requestMap.get("email").toString();
        String password=requestMap.get("password").toString();
        System.out.println(email+" "+password);
        return userService.checkLogin(email, password);
    }

    @RequestMapping("/sendEmail")
    @ResponseBody
    public boolean sendMsg(@RequestBody Map<String,Object> requestMap) {
        System.out.println("sendMsg");
        String email = requestMap.get("email").toString();
        System.out.println(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yummy119@126.com");
        message.setTo(email);
        message.setSubject("yummy验证");
        message.setText("验证码:"+ CodeUtil.getNewCode(email)+",有效期两分钟");
        System.out.println(message.toString());

        try{
            mailSender.send(message);
            System.out.println("send ok");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public int register(@RequestBody Map<String, Object> requestMap){
        System.out.println("register");
        String name=requestMap.get("name").toString();
        String email=requestMap.get("email").toString();
        String password=requestMap.get("password").toString();
        String phone=requestMap.get("phone").toString();
        String identifyCode=requestMap.get("identifyCode").toString();

        if(userService.checkIfRegistered(email)){
            System.out.println("邮箱占用");
            return 1;
        }else if(!CodeUtil.checkCode(email, identifyCode)){
            System.out.println("验证码错误");
            return 2;
        }else{
            userService.addUser(name, password, email, phone);
            return 0;
        }
    }

    @RequestMapping(value = "/getInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Map<String,Object> getInfo(@RequestBody Map<String, Object> requestMap){
        System.out.println("getPersonalInfo");
        int uid= Integer.parseInt((String)requestMap.get("uid"));
        System.out.println(uid);
        return userService.getInfo(uid);
    }

    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean saveInfo(@RequestBody Map<String, Object> requestMap){
        System.out.println("editPersonalInfo");
        userService.modifyInfo(requestMap);
        return true;
    }

    @RequestMapping(value = "/logoff", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean logoff(@RequestBody Map<String, Object> requestMap){
        System.out.println("logoff");
        int uid= Integer.parseInt((String)requestMap.get("uid"));
        return userService.logoff(uid);
    }

}
