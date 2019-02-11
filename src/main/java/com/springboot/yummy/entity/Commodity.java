package com.springboot.yummy.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "commodities")
public class Commodity {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int cid;
    private int rid;
    private String name;
    private double price;
    private int amount;
    private int sold;
    private LocalDate beginDate;
    private LocalDate endDate;

    public Commodity(int rid, String name, double price, int amount, int sold, LocalDate beginDate, LocalDate endDate){
        this.rid=rid;
        this.name=name;
        this.price=price;
        this.amount=amount;
        this.sold=sold;
        this.beginDate=beginDate;
        this.endDate=endDate;
    }

    public Commodity(int cid, int rid, String name, double price, int amount, int sold, LocalDate beginDate, LocalDate endDate){
        this.cid=cid;
        this.rid=rid;
        this.name=name;
        this.price=price;
        this.amount=amount;
        this.sold=sold;
        this.beginDate=beginDate;
        this.endDate=endDate;
    }

    public Commodity(){}

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
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
