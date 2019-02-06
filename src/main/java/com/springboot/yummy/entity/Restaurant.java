package com.springboot.yummy.entity;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    private int rid;
    private String name;
    private String idCode;
    private String password;
    private String location;
    private String region;
    private int owner;
    private String photo;
    private String certificate;
    private boolean ifValid;
    private int kind;

    public Restaurant(int rid, String name, String password, String idCode, String location, String region, int owner, String photo, String certificate, boolean ifValid, int kind) {
        this.password = password;
        this.name = name;
        this.rid=rid;
        this.idCode=idCode;
        this.location=location;
        this.owner=owner;
        this.photo=photo;
        this.certificate=certificate;
        this.ifValid=ifValid;
        this.kind=kind;
        this.region=region;
    }

    public Restaurant() {
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid=rid;
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

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode=idCode;
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
        this.location=region;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner=owner;
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

    public boolean getIfValid() { return ifValid; }

    public void setIfValid(boolean ifValid) { this.ifValid=ifValid; }

}
