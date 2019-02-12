package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.CommodityRepository;
import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.service.CommodityService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("CommodityService")
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    CommodityRepository commodityRepository;

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
            int cid= (Integer)map.get("cid");
            int rid= (Integer)map.get("rid");
            String name=map.get("name").toString();
            Double price = Double.parseDouble(map.get("price").toString());
            int amount= Integer.parseInt((String)map.get("amount"));
            int sold= (Integer)map.get("sold");
            LocalDate beginDate=LocalDate.parse(map.get("beginDate").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate=LocalDate.parse(map.get("endDate").toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            Commodity commodity;
            if(cid==-1){
                commodity=new Commodity(rid, name, price, amount, sold, beginDate, endDate);
            }else{
                commodity=new Commodity(cid, rid, name, price, amount, sold, beginDate, endDate);
            }
            Commodity savedCommodity=commodityRepository.save(commodity);
            return savedCommodity.getCid();
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean deleteCommodity(int cid) {
        try {
            commodityRepository.deleteByCid(cid);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
