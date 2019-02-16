package com.springboot.yummy.service;

import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.entity.PackageDetail;
import com.springboot.yummy.entity.PackageItem;

import java.time.LocalDate;
import java.util.Map;

public interface PackageService {
    Commodity[] getOptions(int rid, LocalDate beginDate, LocalDate endDate);

    boolean savePackage(Map<String, Object> map);

    PackageDetail[] getPackages(int rid);

    boolean deletePackage(int pid);

}
