package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.RestaurantRepository;
import com.springboot.yummy.entity.Restaurant;
import com.springboot.yummy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public boolean addRestaurant(String name, String password, String location, String region, int owner, String photo, String certificate, String kind) {
        int k=0;
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
            return 2;
        }else{
            return 1;
        }
    }
}
