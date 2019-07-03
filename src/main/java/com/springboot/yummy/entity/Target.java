package com.springboot.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "targets")
public class Target implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int tid;
    private int uid;
    private String location;
    private String region;

    public Target(){}

    public Target(int uid, String location, String region) {
        this.uid=uid;
        this.location=location;
        this.region=region;
    }

    public Target(int tid, int uid, String location, String region) {
        this.tid=tid;
        this.uid=uid;
        this.location=location;
        this.region=region;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location=location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region=region;
    }

}
