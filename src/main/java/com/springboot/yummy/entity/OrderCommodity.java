package com.springboot.yummy.entity;

import javax.persistence.*;

@Entity
@Table(name = "orderCommodities")
public class OrderCommodity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int ocid;
    private int oid;
    private int cid;
    private int num;
    private String name;
    private double price;
    @Column(columnDefinition="varchar(255) default '主食'")
    private String kind;

    public OrderCommodity(int oid, int cid, int num, String name, double price, String kind) {
        this.oid = oid;
        this.cid = cid;
        this.num=num;
        this.name = name;
        this.price = price;
        this.kind=kind;
    }

    public OrderCommodity(int ocid, int oid, int cid, int num, String name, double price, String kind) {
        this.ocid = ocid;
        this.oid = oid;
        this.cid = cid;
        this.num=num;
        this.name = name;
        this.price = price;
        this.kind=kind;
    }

    public int getOcid() {
        return ocid;
    }

    public void setOcid(int ocid) {
        this.ocid = ocid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
