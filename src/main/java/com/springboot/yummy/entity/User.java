package com.springboot.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int uid;
    private String name;
    private String email;
    private String password;
    private String phone;
    private int level;
    private double totalCost;
    private boolean ifDelete;

    public User(String name, String password, String email, String phone, int level, double totalCost, boolean ifDelete) {
        this.password = password;
        this.name = name;
        this.email=email;
        this.phone=phone;
        this.level=level;
        this.totalCost=totalCost;
        this.ifDelete=ifDelete;
    }

    public User(int uid, String name, String password, String email, String phone, int level, double totalCost, boolean ifDelete) {
        this.uid=uid;
        this.password = password;
        this.name = name;
        this.email=email;
        this.phone=phone;
        this.level=level;
        this.totalCost=totalCost;
        this.ifDelete=ifDelete;
    }

    public User() {
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email=email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) { this.phone=phone; }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) { this.level=level; }

    public double getTotalCost() { return totalCost; }

    public void setTotalCost(double totalCost) {
        this.totalCost=totalCost;
    }

    public boolean getIfDelete() { return ifDelete; }

    public void setIfDelete(boolean ifDelete) { this.ifDelete=ifDelete; }
}
