package com.springboot.yummy.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modifications")
public class Modification {
    @Id
    private int mid;
    private String name;
    private String password;
    private String location;
    private String region;
    private int rid;
    private String photo;
    private String certificate;
    private int kind;

    public Modification(int mid, String name, String password, String location, String region, int rid, String photo, String certificate, int kind) {
        this.password = password;
        this.name = name;
        this.mid=mid;
        this.location=location;
        this.rid=rid;
        this.photo=photo;
        this.certificate=certificate;
        this.kind=kind;
        this.region=region;
    }

    public Modification(String name, String password, String location, String region, int rid, String photo, String certificate, int kind) {
        this.password = password;
        this.name = name;
        this.location=location;
        this.rid=rid;
        this.photo=photo;
        this.certificate=certificate;
        this.kind=kind;
        this.region=region;
    }

    public Modification() {
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid=mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location=location;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region=region;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid=rid;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) { this.photo=photo; }

    public int getKind() { return kind; }

    public void setKind(int kind) { this.kind=kind; }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) { this.certificate=certificate; }

}
