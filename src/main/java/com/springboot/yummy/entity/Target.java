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

    public Target(){}

    public Target(int uid, String location) {
        this.uid=uid;
        this.location=location;
    }

    public Target(int tid, int uid, String location) {
        this.tid=tid;
        this.uid=uid;
        this.location=location;
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

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location=location;
    }

}
