package com.springboot.yummy.entity;

import org.springframework.format.annotation.DateTimeFormat;

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
    @Column(columnDefinition="varchar(255) default '主食'")
    private String kind;
    private int amount;
    private int sold;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Commodity(int rid, String name, double price, String kind, int amount, int sold, LocalDate beginDate, LocalDate endDate){
        this.rid=rid;
        this.name=name;
        this.price=price;
        this.kind=kind;
        this.amount=amount;
        this.sold=sold;
        this.beginDate=beginDate;
        this.endDate=endDate;
    }

    public Commodity(int cid, int rid, String name, double price, String kind, int amount, int sold, LocalDate beginDate, LocalDate endDate){
        this.cid=cid;
        this.rid=rid;
        this.name=name;
        this.price=price;
        this.kind=kind;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
