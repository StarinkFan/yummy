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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    public Discount(int rid, int total, int discount, LocalDate beginDate, LocalDate endDate) {
        this.rid = rid;
        this.total = total;
        this.discount = discount;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public Discount(int did, int rid, int total, int discount, LocalDate beginDate, LocalDate endDate) {
        this.did=did;
        this.rid = rid;
        this.total = total;
        this.discount = discount;
        this.beginDate = beginDate;
        this.endDate = endDate;
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
