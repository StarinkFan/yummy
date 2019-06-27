package com.springboot.yummy.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @Author:Wang Mo
 * @Description：
 */
@Entity
public class CommodityCart {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int id;
    private int uid;
    private int rid;
    private int cid;
    private int num;

    public CommodityCart() {
    }

    public CommodityCart(int uid, int rid, int cid, int num) {
        this.uid = uid;
        this.rid = rid;
        this.cid = cid;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
