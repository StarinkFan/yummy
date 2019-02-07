package com.springboot.yummy.service;

public interface RestaurantService {
    boolean addRestaurant(String name, String password, String location, String region, int owner, String photo, String certificate, String kind);

    int getApplyState(int owner);
}
