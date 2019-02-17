package com.springboot.yummy.entity;

public class RestaurantDetail {
    private int rid;
    private String name;
    private String location;
    private String region;
    private String photo;
    private int kind;

    private Commodity[] commodities;
    private PackageDetail[] packages;
    private Discount[] discounts;

    public RestaurantDetail(int rid, String name, String location, String region, String photo, int kind, Commodity[] commodities, PackageDetail[] packages, Discount[] discounts) {
        this.name = name;
        this.rid=rid;
        this.location=location;
        this.photo=photo;
        this.kind=kind;
        this.region=region;
        this.commodities=commodities;
        this.packages=packages;
        this.discounts=discounts;
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
}
