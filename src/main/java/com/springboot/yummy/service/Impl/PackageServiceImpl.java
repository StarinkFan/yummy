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
    public List<Commodity> getOptions(int rid) {
        List<Commodity> options=commodityRepository.findByRidAndIfValid(rid, true);
        return options;
    }

    @Override
    public boolean savePackage(Map<String, Object> map) {
        try {
            int pid=(Integer)map.get("pid");
            int rid= (Integer)map.get("rid");
            String name=map.get("name").toString();
            Double price = Double.parseDouble(map.get("price").toString());
            LocalDate beginDate=LocalDate.parse(map.get("beginDate").toString());
            LocalDate endDate=LocalDate.parse(map.get("endDate").toString());
            String description=map.get("description").toString();
            Package aPackage;
            if(pid<0){
                aPackage=new Package(rid, name, 0,price,  description, "销售中", true);
            }else{
                Package p=packageRepository.findFirstByPid(pid);
                aPackage=new Package(pid, rid, name, 0,price, description, p.getState(),p.getIfValid());
            }
            if(hasSameName(aPackage.getRid(), aPackage.getPid(),aPackage.getName())){
               return false;
            }
            aPackage=packageRepository.save(aPackage);
            pid=aPackage.getPid();

            JSONArray list = JSONArray.fromObject(map.get("items"));
            Iterator<Object> it = list.iterator();
            while (it.hasNext()) {
                JSONObject ob = (JSONObject) it.next();
                int cid=ob.getInt("cid");
                int num=ob.getInt("num");
                String iname = ob.getString("name");
                String kind = ob.getString("kind");
                Double iprice = ob.getDouble("price");
                String photo = ob.getString("photo");
                String iDescription = ob.getString("description");
                packageItemRepository.save(new PackageItem(pid, cid, num, iname, iprice, kind, photo, iDescription));
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
            packages[i]=new PackageDetail(aPackage.getPid(), aPackage.getRid(), aPackage.getName(), aPackage.getSold(), aPackage.getPrice(), aPackage.getDescription(), aPackage.getState(), aPackage.getIfValid(),getPackageItems(aPackage.getPid()));
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

    @Override
    public boolean hasSameName(int rid, int pid, String name){
        List<Package> list=packageRepository.findByRid(rid);
        for(Package p: list){
            if(p.getName().equals(name)&&p.getPid()!=pid ){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean validate(int pid) {
        try {
            Package p=packageRepository.findFirstByPid(pid);
            p.setIfValid(true);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean invalidate(int pid) {
        try {
            Package p=packageRepository.findFirstByPid(pid);
            p.setIfValid(false);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
