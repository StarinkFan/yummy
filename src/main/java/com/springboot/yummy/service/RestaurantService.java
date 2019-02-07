package com.springboot.yummy.service;

import com.springboot.yummy.entity.Restaurant;

import java.util.Map;

public interface RestaurantService {
    boolean addRestaurant(String name, String password, String location, String region, int owner, String photo, String certificate, String kind);

    int getApplyState(int owner);

    Map<String,Object> getInfo(int owner);

    Restaurant[] getApplications();

    Restaurant getApplicationDetail(int applicationId);
}
