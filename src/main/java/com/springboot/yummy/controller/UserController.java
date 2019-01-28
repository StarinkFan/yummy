package com.springboot.yummy.controller;

import com.springboot.yummy.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired  //自动装配
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public int login(HttpServletRequest request, @RequestBody Map<String, Object> requestMap){
        System.out.println("login");
        String email=requestMap.get("email").toString();
        String password=requestMap.get("password").toString();
        System.out.println(email+" "+password);
        return userService.checkLogin(email, password);
    }

//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public int login(@NotNull(message = "邮箱不能为空") String email, @NotNull(message = "密码不能为空") String password) {
//        System.out.println("login connect");
//        return userService.checkLogin(email, password);
//    }

}
