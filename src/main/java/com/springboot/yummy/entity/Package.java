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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Package(int rid, String name, double price, LocalDate beginDate, LocalDate endDate) {
        this.rid = rid;
        this.name = name;
        this.price = price;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Package(int pid, int rid, String name, double price, LocalDate beginDate, LocalDate endDate) {
        this.pid=pid;
        this.rid = rid;
        this.name = name;
        this.price = price;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

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

    public LocalDate getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDate beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}