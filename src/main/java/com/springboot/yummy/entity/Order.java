package com.springboot.yummy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int oid;
    private int uid;
    private int rid;
    private double total;
    private double discount;
    private double pay;
    private double refund;
    private int state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refundTime;

    public Order(){}

    public Order(int oid, int uid, int rid, double total, double discount, double pay, double refund, int state, LocalDateTime createTime, LocalDateTime payTime, LocalDateTime refundTime){
        this.oid=oid;
        this.uid=uid;
        this.rid=rid;
        this.total=total;
        this.discount=discount;
        this.pay=pay;
        this.refund=refund;
        this.state=state;
        this.createTime=createTime;
        this.payTime=payTime;
        this.refundTime=refundTime;
    }

    public Order(int uid, int rid, double total, double discount, double pay, double refund, int state, LocalDateTime createTime, LocalDateTime payTime, LocalDateTime refundTime){
        this.uid=uid;
        this.rid=rid;
        this.total=total;
        this.discount=discount;
        this.pay=pay;
        this.refund=refund;
        this.state=state;
        this.createTime=createTime;
        this.payTime=payTime;
        this.refundTime=refundTime;
    }


    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPay() {
        return pay;
    }

    public void setPay(double pay) {
        this.pay = pay;
    }

    public double getRefund() {
        return refund;
    }

    public void setRefund(double refund) {
        this.refund = refund;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime=createTime;
    }

    public LocalDateTime getPayTime() {
        return payTime;
    }

    public void setPayTime(LocalDateTime payTime) {
        this.payTime=payTime;
    }

    public LocalDateTime getRefundTime() {
        return createTime;
    }

    public void setRefundTime(LocalDateTime refundTime) {
        this.refundTime=refundTime;
    }
}
