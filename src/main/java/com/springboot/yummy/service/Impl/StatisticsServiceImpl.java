package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.GainRepository;
import com.springboot.yummy.dao.OrderRepository;
import com.springboot.yummy.dao.RestaurantRepository;
import com.springboot.yummy.dao.UserRepository;
import com.springboot.yummy.entity.Gain;
import com.springboot.yummy.entity.Order;
import com.springboot.yummy.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
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

    @Override
    public Map<String, Object> getPersonalCondition(int uid) {
        Map<String,Object> map=new HashMap<>();
        List<Order> orders=orderRepository.findByUidAndState(uid, 2);
        int[] timeCounts= {0,0,0};
        int[] payCounts={0,0,0,0};
        int[] restaurantCounts={0,0,0,0,0,0};
        for(Order order: orders){
            long days=Period.between(order.getArrivalTime().toLocalDate(), LocalDate.now()).getDays();
            if(days==2){
                timeCounts[0]++;
            }else if(days==1){
                timeCounts[1]++;
            }else if(days==0){
                timeCounts[2]++;
            }
            double pay=order.getPay();
            if(pay<=30){
                payCounts[0]++;
            }else if(pay<=80){
                payCounts[1]++;
            }else if(pay<=200){
                payCounts[2]++;
            }else{
                payCounts[3]++;
            }
            restaurantCounts[restaurantRepository.findFirstByRid(order.getRid()).getKind()-1]++;
        }
        orders=orderRepository.findByUidAndState(uid, 3);
        int[] timeCounts2= {0,0,0};
        for(Order order: orders){
            int days=Period.between(order.getRefundTime().toLocalDate(), LocalDate.now()).getDays();
            if(days==2){
                timeCounts2[0]++;
            }else if(days==1){
                timeCounts2[1]++;
            }else if(days==0){
                timeCounts2[2]++;
            }
        }
        map.put("timeCounts", timeCounts);
        map.put("timeCounts2", timeCounts2);
        map.put("payCounts", payCounts);
        map.put("restaurantCounts", restaurantCounts);

        return map;
    }
}
