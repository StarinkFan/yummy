package com.springboot.yummy.service;

public interface ModificationService {
    boolean hasModification(int owner);

    boolean addModification(String name, String password, String location, String region, int rid, String photo, String certificate, String kind);
}
