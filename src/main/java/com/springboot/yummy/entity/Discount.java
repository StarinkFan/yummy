package com.springboot.yummy.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "discounts")
public class Discount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int did;
    private int rid;
    private int total;
    private int discount;
    @Column(columnDefinition="int default 0")
    private int time;
    @Column(columnDefinition="varchar(255) default '使用中'")
    private String state;
    @Column(columnDefinition="bit(1) default 1")
    private boolean ifValid;

    public Discount(int rid, int total, int discount, int time, String state, boolean ifValid) {
        this.rid = rid;
        this.total = total;
        this.discount = discount;
        this.time=time;
        this.state=state;
        this.ifValid=ifValid;
    }

    public Discount(int did, int rid, int total, int discount, int time, String state, boolean ifValid) {
        this.did=did;
        this.rid = rid;
        this.total = total;
        this.discount = discount;
        this.time=time;
        this.state=state;
        this.ifValid=ifValid;
    }

    public Discount(){}

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }
}
