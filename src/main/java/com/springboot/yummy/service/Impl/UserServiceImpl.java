package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.TargetRepository;
import com.springboot.yummy.dao.UserRepository;
import com.springboot.yummy.entity.Target;
import com.springboot.yummy.entity.User;
import com.springboot.yummy.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TargetRepository targetRepository;


    @Override
    public Map<String,Object> checkLogin(String email, String password) {
        Map<String,Object> resultMap=new HashMap<>();
        User user=userRepository.findFirstByEmailAndPassword(email, password);
        if(user!=null&&(!user.getIfDelete())){
            if(user.getEmail().equals("yummy119@126.com")){
                resultMap.put("code", 3);
            }else{
                resultMap.put("code", 1);
                resultMap.put("uid",userRepository.findFirstByEmail(email).getUid());
            }
        }else{
            resultMap.put("code", 2);
        }
        return resultMap;
    }

    @Override
    public boolean checkIfRegistered(String email) {
        User user=userRepository.findFirstByEmail(email);
        if(user!=null&&(!user.getIfDelete())){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void addUser(String name, String password, String email, String phone) {
        User user=new User(name, password, email, phone, 0, 0.00, false);
        userRepository.save(user);
    }

    @Override
    public Map<String, Object> getInfo(int uid) {
        Map<String,Object> resultMap=new HashMap<>();
        User user=userRepository.findFirstByUid(uid);
        resultMap.put("name", user.getName());
        resultMap.put("email", user.getEmail());
        resultMap.put("level", user.getLevel());
        resultMap.put("phone", user.getPhone());
        resultMap.put("password", user.getPassword());
        List<Target> targets= targetRepository.findByUid(uid);
        resultMap.put("addresses", targets);
        return  resultMap;
    }

    @Override
    public void modifyInfo(Map<String, Object> map) {
        int uid= Integer.parseInt((String)map.get("uid"));
        String name=(String)map.get("name");
        String email=userRepository.findFirstByUid(uid).getEmail();
        int level= userRepository.findFirstByUid(uid).getLevel();
        String phone=map.get("phone").toString();
        String password=map.get("password").toString();
        double totalCost=userRepository.findFirstByUid(uid).getTotalCost();
        User user=new User(uid, name, password, email, phone, level, totalCost, false);
        userRepository.save(user);

        JSONArray list = JSONArray.fromObject(map.get("addresses"));
        Iterator<Object> it = list.iterator();
        while (it.hasNext()) {
            JSONObject ob = (JSONObject) it.next();
            int tid=ob.getInt("tid");
            int userid=ob.getInt("uid");
            String location = ob.getString("location");
            Target target;
            if(tid==0){
                target=new Target(userid, location);
            }else{
                target=new Target(tid, userid, location);
            }
            targetRepository.save(target);
        }


    }

}
