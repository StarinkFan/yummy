package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.DiscountRepository;
import com.springboot.yummy.entity.Discount;
import com.springboot.yummy.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service("DiscountService")
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    @Override
    public Discount[] getDiscounts(int rid) {
        List<Discount> list = discountRepository.findByRid(rid);
        int length=list.size();
        Discount[] discounts=new Discount[length];
        for(int i=0;i<length;i++){
            discounts[i]=list.get(i);
        }
        return discounts;
    }

    @Override
    public boolean saveDiscount(Map<String, Object> map) {
        try {
            int rid= (Integer)map.get("rid");
            int total= Integer.parseInt((String)map.get("total"));
            int dis= Integer.parseInt((String)map.get("discount"));
            LocalDate beginDate=LocalDate.parse(map.get("beginDate").toString());
            LocalDate endDate=LocalDate.parse(map.get("endDate").toString());
            Discount discount;
            discount=new Discount(rid, total, dis, beginDate, endDate);
            if(!hasSameDiscount(discount)){
                Discount savedDiscount=discountRepository.save(discount);
                return true;
            }else{
                return false;
            }

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteDiscount(int did) {
        try {
            discountRepository.deleteByDid(did);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean hasSameDiscount(Discount discount){
        List<Discount> list=discountRepository.findByRid(discount.getRid());
        for(Discount d: list){
            if(d.getTotal()==discount.getTotal()&& !(d.getEndDate().isBefore(discount.getBeginDate())||d.getBeginDate().isAfter(discount.getEndDate())) ){
                return true;
            }
        }
        return false;
    }
}
