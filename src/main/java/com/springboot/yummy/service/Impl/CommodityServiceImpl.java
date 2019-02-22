package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.CommodityRepository;
import com.springboot.yummy.dao.PackageItemRepository;
import com.springboot.yummy.dao.PackageRepository;
import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.entity.PackageItem;
import com.springboot.yummy.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service("CommodityService")
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityRepository commodityRepository;
    @Autowired
    PackageItemRepository packageItemRepository;

    @Override
    public Commodity[] getCommodities(int rid) {
        List<Commodity> list = commodityRepository.findByRid(rid);
        int length=list.size();
        Commodity[] commodities=new Commodity[length];
        for(int i=0;i<length;i++){
            commodities[i]=list.get(i);
        }
        return commodities;
    }

    @Override
    public int saveCommodity(Map<String, Object> map) {
        try {
            int rid= (Integer)map.get("rid");
            String name=map.get("name").toString();
            String kind=map.get("kind").toString();
            Double price = Double.parseDouble(map.get("price").toString());
            int amount= Integer.parseInt((String)map.get("amount"));
            int sold= (Integer)map.get("sold");
            LocalDate beginDate=LocalDate.parse(map.get("beginDate").toString());
            LocalDate endDate=LocalDate.parse(map.get("endDate").toString());
            Commodity commodity;
            commodity=new Commodity(rid, name, price, kind, amount, sold, beginDate, endDate);
            if(!hasSameCommodity(commodity)){
                Commodity savedCommodity=commodityRepository.save(commodity);
                return savedCommodity.getCid();
            }else{
                return -2;
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    private boolean hasSameCommodity(Commodity commodity){
        List<Commodity> list=commodityRepository.findByRid(commodity.getRid());
        for(Commodity c: list){
            if(c.getName().equals(commodity.getName())&& !(c.getEndDate().isBefore(commodity.getBeginDate())||c.getBeginDate().isAfter(commodity.getEndDate())) ){
                return true;
            }
        }
        return false;
    }

    @Override
    public int deleteCommodity(int cid) {
        try {
            if(inPackage(cid)){
                return -1;
            }
            commodityRepository.deleteByCid(cid);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    private boolean inPackage(int cid){
        List<PackageItem> items=packageItemRepository.findAll();
        for(PackageItem item: items){
            if(item.getCid()==cid) {
                return true;
            }
        }
        return false;
    }
}
