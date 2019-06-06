package com.springboot.yummy.service;

import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.entity.PackageDetail;
import com.springboot.yummy.entity.PackageItem;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface PackageService {
    List<Commodity> getOptions(int rid);

    boolean savePackage(Map<String, Object> map);

    PackageDetail[] getPackages(int rid);

    boolean deletePackage(int pid);

}
