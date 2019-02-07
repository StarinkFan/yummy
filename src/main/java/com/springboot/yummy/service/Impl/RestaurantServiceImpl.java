package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.RestaurantRepository;
import com.springboot.yummy.entity.Restaurant;
import com.springboot.yummy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public boolean addRestaurant(String name, String password, String location, String region, int owner, String photo, String certificate, String kind) {
        int k;
        switch (kind){
            case "中餐":
                k=1;
                break;
            case "西餐":
                k=2;
                break;
            case "快餐":
                k=3;
                break;
            case "甜品":
                k=4;
                break;
            case "小吃":
                k=5;
                break;
            case "其他":
                k=6;
                break;
            default:
                return false;
        }
        try{
            Restaurant restaurant=new Restaurant(name, password, "", location, region, owner, photo, certificate, false, k);
            restaurantRepository.save(restaurant);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getApplyState(int owner) {
        Restaurant restaurant=restaurantRepository.findFirstByOwner(owner);
        if(restaurant==null){
            return 0;
        }else if(restaurant.getIfValid()){
            return 3;
        }else if(restaurant.getIdCode().equals("")){
            return 1;
        }else{
            return 2;
        }
    }

    @Override
    public Map<String, Object> getInfo(int owner) {
        Restaurant restaurant=restaurantRepository.findFirstByOwner(owner);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("kind", restaurant.getKind());
        resultMap.put("name", restaurant.getName());
        resultMap.put("password", restaurant.getPassword());
        resultMap.put("photoSrc", restaurant.getPhoto());
        resultMap.put("certificateSrc", restaurant.getCertificate());
        resultMap.put("location", restaurant.getLocation());
        resultMap.put("region", restaurant.getRegion());

        return resultMap;
    }

    @Override
    public Restaurant[] getApplications() {
        List<Restaurant> list=restaurantRepository.findAllByIfValidAndIdCode(false, "");
        int length=list.size();
        Restaurant[] applications=new Restaurant[length];
        for(int i=0;i<length;i++){
            applications[i]=list.get(i);
        }
        return applications;
    }

    @Override
    public Restaurant getApplicationDetail(int applicationId) {
        return restaurantRepository.findFirstByRid(applicationId);
    }
}
