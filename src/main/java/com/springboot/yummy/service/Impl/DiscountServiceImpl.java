package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.DiscountRepository;
import com.springboot.yummy.entity.Discount;
import com.springboot.yummy.entity.Package;
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
    public int saveDiscount(Map<String, Object> map) {
        try {
            int rid= (Integer)map.get("rid");
            int total= Integer.parseInt((String)map.get("total"));
            int dis= Integer.parseInt((String)map.get("discount"));
            if(total<dis){
                return -2;
            }
            LocalDate beginDate=LocalDate.parse(map.get("beginDate").toString());
            LocalDate endDate=LocalDate.parse(map.get("endDate").toString());
            Discount discount;
            discount=new Discount(rid, total, dis, 0, "使用中", true);
            if(!hasSameDiscount(discount.getRid(), discount.getDid(), discount.getTotal())){
                Discount savedDiscount=discountRepository.save(discount);
                return savedDiscount.getDid();
            }else{
                return -1;
            }

        }catch (Exception e){
            e.printStackTrace();
            return -3;
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

    @Override
    public boolean validate(int did) {
        try {
            Discount d=discountRepository.findFirstByDid(did);
            d.setIfValid(true);
            d.setState("使用中");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean invalidate(int did) {
        try {
            Discount d=discountRepository.findFirstByDid(did);
            d.setIfValid(false);
            d.setState("未使用");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private boolean hasSameDiscount(int rid, int did, int total){
        List<Discount> list=discountRepository.findByRid(rid);
        for(Discount d: list){
            if(d.getTotal()==total&&d.getDid()!=did ){
                return true;
            }
        }
        return false;
    }
}
