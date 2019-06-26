package com.springboot.yummy.vo;

/**
 * @Author:Wang Mo
 * @Description：
 */
public class CanConveyVO {
    int time;
    double distance;

    public CanConveyVO(int time, double distance) {
        this.time = time;
        this.distance = distance;
    }

    public CanConveyVO() {
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
