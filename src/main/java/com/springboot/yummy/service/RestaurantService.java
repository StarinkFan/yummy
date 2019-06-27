package com.springboot.yummy.service;

import com.springboot.yummy.entity.Restaurant;
import com.springboot.yummy.entity.RestaurantDetail;

import java.util.List;
import java.util.Map;

public interface RestaurantService {
    boolean addRestaurant(int rid, String name, String password, String location, String region, int owner, String photo, String certificate, String kind);

    int getApplyState(int owner);

    Map<String,Object> getInfo(int owner);

    Restaurant[] getApplications();

    Restaurant getApplicationDetail(int applicationId);

    boolean pass(int applicationId);

    boolean veto(int applicationId);

    String getIdCode(int owner);

    String getName(int rid);

    Restaurant[] getRestaurantList();

    RestaurantDetail getRestaurantDetailByUser(int rid,int uid);

    List<String> getRestaurantAddresses();

    Restaurant modifyRestaurant(Restaurant restaurant);
}
