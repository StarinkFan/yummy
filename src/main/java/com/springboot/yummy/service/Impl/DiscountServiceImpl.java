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
            int did=(Integer)map.get("did");
            int rid= (Integer)map.get("rid");
            int total= (Integer)map.get("total");
            int dis= (Integer)map.get("discount");
            if(total<dis){
                return -2;
            }
            Discount discount;
            if(did<0){
                discount=new Discount(rid, total, dis, 0, "使用中", true);
            }else{
                Discount d1=discountRepository.findFirstByDid(did);
                discount=new Discount(did, rid, total, dis, d1.getTime(), d1.getState(), d1.isIfValid());
            }
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

    @Override
    public boolean hasSameDiscount(int rid, int did, int total){
        List<Discount> list=discountRepository.findByRid(rid);
        for(Discount d: list){
            if(d.getTotal()==total&&d.getDid()!=did ){
                return true;
            }
        }
        return false;
    }
}
