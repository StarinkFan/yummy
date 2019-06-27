package com.springboot.yummy.vo;

/**
 * @Author:Wang Mo
 * @Description：
 */
public class CommodityVO {
    int cid;
    int rid;
    String name;
    double price;
    String kind;
    int sold;
    String photo;
    String description;
     String state;
     boolean ifValid;
     int num;

    public CommodityVO() {
    }

    public CommodityVO(int cid, int rid, String name, double price, String kind, int sold, String photo, String description, String state, boolean ifValid, int num) {
        this.cid = cid;
        this.rid = rid;
        this.name = name;
        this.price = price;
        this.kind = kind;
        this.sold = sold;
        this.photo = photo;
        this.description = description;
        this.state = state;
        this.ifValid = ifValid;
        this.num = num;
    }

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

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
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

    public boolean isIfValid() {
        return ifValid;
    }

    public void setIfValid(boolean ifValid) {
        this.ifValid = ifValid;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
