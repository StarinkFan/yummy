package com.springboot.yummy.service;

import com.springboot.yummy.entity.Modification;

public interface ModificationService {
    boolean hasModification(int owner);

    boolean addModification(String name, String password, String location, String region, int rid, String photo, String certificate, String kind);

    Modification[] getModifications();

    Modification getModificationDetail(int mid);

    boolean pass(int mid);

    boolean veto(int mid);
}
