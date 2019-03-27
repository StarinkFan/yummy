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
    @Column(columnDefinition="varchar(255) default 'Starink'")
    private String uname;
    @Column(columnDefinition="varchar(255) default '和园九十度西餐'")
    private String rname;
    @Column(columnDefinition="float(10,2) default '0.00'")
    private double total;
    @Column(columnDefinition="float(10,2) default '0.00'")
    private double discount;
    @Column(columnDefinition="float(10,2) default '0.00'")
    private double pay;
    @Column(columnDefinition="float(10,2) default '0.00'")
    private double refund;
    private String target;
    private int state;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime payTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime refundTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime arrivalTime;
    @Column(columnDefinition="int default 1")
    private int bankAccount;

    public Order(){}

    public Order(int oid, int uid, int rid, double total, double discount, double pay, double refund, String target, int state, LocalDateTime createTime, LocalDateTime payTime, LocalDateTime refundTime, LocalDateTime arrivalTime, String uname, String rname){
        this.oid=oid;
        this.uid=uid;
        this.rid=rid;
        this.total=total;
        this.discount=discount;
        this.pay=pay;
        this.refund=refund;
        this.target=target;
        this.state=state;
        this.createTime=createTime;
        this.payTime=payTime;
        this.refundTime=refundTime;
        this.arrivalTime=arrivalTime;
        this.uname=uname;
        this.rname=rname;
    }

    public Order(int uid, int rid, double total, double discount, double pay, double refund, String target, int state, LocalDateTime createTime, LocalDateTime payTime, LocalDateTime refundTime, LocalDateTime arrivalTime, String uname, String rname){
        this.uid=uid;
        this.rid=rid;
        this.total=total;
        this.discount=discount;
        this.pay=pay;
        this.refund=refund;
        this.target=target;
        this.state=state;
        this.createTime=createTime;
        this.payTime=payTime;
        this.refundTime=refundTime;
        this.arrivalTime=arrivalTime;
        this.uname=uname;
        this.rname=rname;
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
        return refundTime;
    }

    public void setRefundTime(LocalDateTime refundTime) {
        this.refundTime=refundTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime=arrivalTime;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public int getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(int bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
