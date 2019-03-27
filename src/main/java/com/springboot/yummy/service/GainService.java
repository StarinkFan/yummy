package com.springboot.yummy.service;

import java.util.Map;

public interface GainService {
    boolean addGain(int oid);
    Map<String,Object> getMonthGain(int rid);
}
