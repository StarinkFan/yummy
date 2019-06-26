package com.springboot.yummy.vo;

/**
 * @Author:Wang Mo
 * @Description：
 */
public class RestaurantVO {
    private int rid;
    private String name;
    private String location;
    private String region;
    private String photo;
    private int kind;
    private int time;
    private double distance;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public RestaurantVO() {
    }

    public RestaurantVO(int rid, String name, String location, String region, String photo, int kind, int time,double distance) {
        this.rid = rid;
        this.name = name;
        this.location = location;
        this.region = region;
        this.photo = photo;
        this.kind = kind;
        this.time = time;
        this.distance=distance;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
