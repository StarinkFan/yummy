package com.springboot.yummy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "gains")
public class Gain {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int gid;
    private int rid;
    @Column(columnDefinition="float(10,2) default '0.00'")
    private double total;
    @Column(columnDefinition="float(10,2) default '0.00'")
    private double rGain;
    @Column(columnDefinition="float(10,2) default '0.00'")
    private double sGain;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    public Gain(){}

    public Gain(int rid, double total, double rGain, double sGain, LocalDateTime time){
        this.rid=rid;
        this.total=total;
        this.rGain=rGain;
        this.sGain=sGain;
        this.time=time;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getrGain() {
        return rGain;
    }

    public void setrGain(double rGain) {
        this.rGain = rGain;
    }

    public double getsGain() {
        return sGain;
    }

    public void setsGain(double sGain) {
        this.sGain = sGain;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
