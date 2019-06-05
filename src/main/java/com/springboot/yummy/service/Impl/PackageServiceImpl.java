package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.CommodityRepository;
import com.springboot.yummy.dao.PackageItemRepository;
import com.springboot.yummy.dao.PackageRepository;
import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.entity.PackageDetail;
import com.springboot.yummy.entity.PackageItem;
import com.springboot.yummy.service.PackageService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("PackageService")
public class PackageServiceImpl implements PackageService {
    @Autowired
    CommodityRepository commodityRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    PackageItemRepository packageItemRepository;

    @Override
    public Commodity[] getOptions(int rid, LocalDate beginDate, LocalDate endDate) {
        List<Commodity> commodities=commodityRepository.findByRid(rid);
        List<Commodity> items=new ArrayList<>();
        for(int i=0;i<commodities.size();i++){
            items.add(commodities.get(i));
        }
        int length=items.size();
        Commodity[] options=new Commodity[length];
        for(int i=0;i<length;i++){
            options[i]=items.get(i);
        }
        return options;
    }

    @Override
    public boolean savePackage(Map<String, Object> map) {
        try {
            int rid= (Integer)map.get("rid");
            String name=map.get("name").toString();
            Double price = Double.parseDouble(map.get("price").toString());
            LocalDate beginDate=LocalDate.parse(map.get("beginDate").toString());
            LocalDate endDate=LocalDate.parse(map.get("endDate").toString());
            Package aPackage=new Package(rid, name, price, beginDate, endDate);
            if(hasSamePackage(aPackage)){
               return false;
            }
            aPackage=packageRepository.save(aPackage);
            int pid=aPackage.getPid();

            JSONArray list = JSONArray.fromObject(map.get("items"));
            Iterator<Object> it = list.iterator();
            while (it.hasNext()) {
                JSONObject ob = (JSONObject) it.next();
                int cid=ob.getInt("cid");
                int num=ob.getInt("num");
                String iname = ob.getString("name");
                String kind = ob.getString("kind");
                Double iprice = ob.getDouble("price");
                packageItemRepository.save(new PackageItem(pid, cid, num, iname, iprice, kind));
            }

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public PackageDetail[] getPackages(int rid) {
        List<Package> list = packageRepository.findByRid(rid);

        int length=list.size();
        PackageDetail[] packages=new PackageDetail[length];
        for(int i=0;i<length;i++){
            Package aPackage=list.get(i);
            packages[i]=new PackageDetail(aPackage.getPid(), aPackage.getRid(), aPackage.getName(), aPackage.getPrice(), aPackage.getBeginDate(), aPackage.getEndDate(), getPackageItems(aPackage.getPid()));
        }
        return packages;
    }

    @Override
    public boolean deletePackage(int pid) {
        try{
            packageRepository.deleteByPid(pid);
            List<PackageItem> items=packageItemRepository.findByPid(pid);
            for(PackageItem item: items){
                packageItemRepository.delete(item);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    private PackageItem[] getPackageItems(int pid) {
        List<PackageItem> list = packageItemRepository.findByPid(pid);
        int length=list.size();
        PackageItem[] packageItems=new PackageItem[length];
        for(int i=0;i<length;i++){
            packageItems[i]=list.get(i);
        }
        return packageItems;
    }

    private boolean hasSamePackage(Package aPackage){
        List<Package> list=packageRepository.findByRid(aPackage.getRid());
        for(Package p: list){
            if(p.getName().equals(aPackage.getName())&& !(p.getEndDate().isBefore(aPackage.getBeginDate())||p.getBeginDate().isAfter(aPackage.getEndDate())) ){
                return true;
            }
        }
        return false;
    }
}
