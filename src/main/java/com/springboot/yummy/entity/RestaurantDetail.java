package com.springboot.yummy.entity;

public class RestaurantDetail {
    private int rid;
    private String name;
    private String location;
    private String region;
    private String photo;
    private int kind;

    private Commodity[] commodities;
    private int[] commodityNum;
    private PackageDetail[] packages;
    private int[] packageNum;
    private Discount[] discounts;


    public RestaurantDetail(int rid, String name, String location, String region, String photo, int kind, Commodity[] commodities, int[] commodityNum, PackageDetail[] packages, int[] packageNum, Discount[] discounts) {
        this.rid = rid;
        this.name = name;
        this.location = location;
        this.region = region;
        this.photo = photo;
        this.kind = kind;
        this.commodities = commodities;
        this.commodityNum = commodityNum;
        this.packages = packages;
        this.packageNum = packageNum;
        this.discounts = discounts;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public Commodity[] getCommodities() {
        return commodities;
    }

    public void setCommodities(Commodity[] commodities) {
        this.commodities = commodities;
    }

    public PackageDetail[] getPackages() {
        return packages;
    }

    public void setPackages(PackageDetail[] packages) {
        this.packages = packages;
    }

    public Discount[] getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Discount[] discounts) {
        this.discounts = discounts;
    }

    public int[] getCommodityNum() {
        return commodityNum;
    }

    public void setCommodityNum(int[] commodityNum) {
        this.commodityNum = commodityNum;
    }

    public int[] getPackageNum() {
        return packageNum;
    }

    public void setPackageNum(int[] packageNum) {
        this.packageNum = packageNum;
    }
}
