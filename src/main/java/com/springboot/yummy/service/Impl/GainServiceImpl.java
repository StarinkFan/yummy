package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.GainRepository;
import com.springboot.yummy.dao.OrderRepository;
import com.springboot.yummy.dao.RuleRepository;
import com.springboot.yummy.entity.Gain;
import com.springboot.yummy.entity.Order;
import com.springboot.yummy.entity.Rule;
import com.springboot.yummy.service.GainService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("GainService")
public class GainServiceImpl implements GainService {
    @Autowired
    GainRepository gainRepository;
    @Autowired
    RuleRepository ruleRepository;
    @Autowired
    OrderRepository orderRepository;


    @Override
    public boolean addGain(int oid) {
        try {
            Order order=orderRepository.findFirstByOid(oid);
            double total=order.getPay()-order.getRefund();
            List<Rule> rules=ruleRepository.findByTopGreaterThanOrderByTop(0);
            int commonPercent=ruleRepository.findFirstByRuid(1).getPercent();
            int index=-1;
            for(int i=0; i<rules.size();i++){
                if(total<rules.get(i).getTop()){
                    index=i;
                    break;
                }
            }
            double sGain;
            if(index>=0){
                sGain=total*rules.get(index).getPercent()/100;
            }else{
                sGain=total*commonPercent/100;
            }
            gainRepository.save(new Gain(order.getRid(), total, total-sGain, sGain, LocalDateTime.now()));
            System.out.println("订单"+oid+"利润结算完成");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Map<String, Object> getMonthGain(int rid) {
        return null;
    }
}
