package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.GainRepository;
import com.springboot.yummy.dao.OrderRepository;
import com.springboot.yummy.dao.RestaurantRepository;
import com.springboot.yummy.dao.UserRepository;
import com.springboot.yummy.entity.Gain;
import com.springboot.yummy.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("StatisticsService")
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    GainRepository gainRepository;


    @Override
    public Map<String, Object> getSystemCondition() {
        Map<String,Object> map=new HashMap<>();
        int userNum=userRepository.findAll().size()-1;
        int restaurantNum=restaurantRepository.findAll().size();
        DecimalFormat df = new DecimalFormat(".00");
        double sTotalGain=0;
        double rTotalGain=0;
        double sMonthGain=0;
        double rMonthGain=0;
        List<Gain> gains=gainRepository.findAll();
        for(Gain gain:gains){
            sTotalGain=sTotalGain+gain.getsGain();
            rTotalGain=rTotalGain+gain.getrGain();
            Duration duration=Duration.between(gain.getTime(), LocalDateTime.now());
            long days=duration.toDays();
            if(days<=30){
                sMonthGain=sMonthGain+gain.getsGain();
                rMonthGain=rMonthGain+gain.getrGain();
            }
        }
        map.put("userNum", userNum);
        map.put("restaurantNum", restaurantNum);
        map.put("sTotalGain", df.format(sTotalGain));
        map.put("rTotalGain", df.format(rTotalGain));
        map.put("sMonthGain", df.format(sMonthGain));
        map.put("rMonthGain", df.format(rMonthGain));
        return map;
    }
}
