package com.springboot.yummy.service;

import java.util.Map;

public interface StatisticsService {
    Map<String, Object> getSystemCondition();

    Map<String, Object> getPersonalCondition(int uid);
}
