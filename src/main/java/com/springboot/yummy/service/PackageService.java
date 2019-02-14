package com.springboot.yummy.service;

import com.springboot.yummy.entity.Commodity;

import java.time.LocalDate;

public interface PackageService {
    Commodity[] getOptions(int rid, LocalDate beginDate, LocalDate endDate);
}
