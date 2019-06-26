package com.springboot.yummy.service;

public interface AddressService {
    String[] getSimilarLocations(String query, String region);

    int canConvey(String departure, String target);

    String getLocation(String address);

    int[] getDistanceAndTime(String departure, String target);
}
