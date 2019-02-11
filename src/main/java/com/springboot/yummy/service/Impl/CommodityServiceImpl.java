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
    public boolean saveCommodities(Map<String, Object> map) {
        try {
            JSONArray list = JSONArray.fromObject(map.get("commodities"));
            Iterator<Object> it = list.iterator();
            while (it.hasNext()) {
                JSONObject ob = (JSONObject) it.next();
                int rid=ob.getInt("rid");
                int cid=ob.getInt("cid");
                String name = ob.getString("name");
                Double price = Double.parseDouble(ob.getString("region"));
                int amount= Integer.parseInt(ob.getString("amount"));
                int sold=ob.getInt("sold");
                LocalDate beginDate=LocalDate.parse(ob.getString("beginDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate endDate=LocalDate.parse(ob.getString("endDate"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Commodity commodity;
                if(cid==-1){
                    commodity=new Commodity(rid, name, price, amount, sold, beginDate, endDate);
                }else{
                    commodity=new Commodity(cid, rid, name, price, amount, sold, beginDate, endDate);
                }
                commodityRepository.save(commodity);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
