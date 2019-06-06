package com.springboot.yummy.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "packages")
public class Package {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int pid;
    private int rid;
    private String name;
    private double price;
    @Column(columnDefinition="varchar(255) default 'https://njuhzl.oss-cn-hangzhou.aliyuncs.com/yummy/defaultLackPic.png'")
    private String photo;
    @Column(columnDefinition="varchar(255) default ''")
    private String description;
    @Column(columnDefinition="varchar(255) default '销售中'")
    private String state;
    @Column(columnDefinition="bit(1) default 1")
    private boolean ifValid;

    public Package(int rid, String name, double price, String photo, String description, String state, boolean ifValid) {
        this.rid = rid;
        this.name = name;
        this.price = price;
        this.photo=photo;
        this.description=description;
        this.state=state;
        this.ifValid=ifValid;
    }

    public Package(int pid, int rid, String name, double price, String photo, String description, String state, boolean ifValid) {
        this.pid=pid;
        this.rid = rid;
        this.name = name;
        this.price = price;
        this.photo=photo;
        this.description=description;
        this.state=state;
        this.ifValid=ifValid;
    }

    public Package(){}

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }
}