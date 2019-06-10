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
            int cid=(Integer)map.get("cid");
            int rid= (Integer)map.get("rid");
            String name=map.get("name").toString();
            String kind=map.get("kind").toString();
            Double price = Double.parseDouble(map.get("price").toString());
            String photo=map.get("photo").toString();
            String description=map.get("description").toString();
            Commodity commodity;
            if(cid<0){
                commodity=new Commodity(rid, name, price, kind, 0, photo, description, "销售中", true);
            }else {
                Commodity commodity1=commodityRepository.findFirstByCid(cid);
                commodity=new Commodity(cid, rid, name, price, kind, commodity1.getSold(), photo, description, commodity1.getState(), commodity1.getIfValid());
            }
            if(!hasSameName(commodity.getRid(), commodity.getCid(), commodity.getName())){
                Commodity savedCommodity=commodityRepository.save(commodity);
                adjustCommodityInPackages(savedCommodity);
                return savedCommodity.getCid();
            }else{
                return -2;
            }

        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean hasSameName(int rid, int cid, String name){
        List<Commodity> list=commodityRepository.findByRid(rid);
        for(Commodity c: list){
            if(c.getName().equals(name)&&c.getCid()!=cid ){
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

    @Override
    public boolean validate(int cid) {
        try {
            Commodity c=commodityRepository.findFirstByCid(cid);
            c.setIfValid(true);
            c.setState("销售中");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean invalidate(int cid) {
        try {
            if(inPackage(cid)){
                return false;
            }
            Commodity c=commodityRepository.findFirstByCid(cid);
            c.setIfValid(false);
            c.setState("已下架");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
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

    private void adjustCommodityInPackages(Commodity commodity){
        List<PackageItem> items=packageItemRepository.findAll();
        for(PackageItem item: items){
            if(item.getCid()==commodity.getCid()) {
                item.setKind(commodity.getKind());
                item.setName(commodity.getName());
                item.setPrice(commodity.getPrice());
            }
        }
    }
}
