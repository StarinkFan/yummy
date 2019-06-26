package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.*;
import com.springboot.yummy.entity.*;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.service.PackageService;
import com.springboot.yummy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("RestaurantService")
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    RestaurantRepository restaurantRepository;
    @Autowired
    CommodityRepository commodityRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    PackageItemRepository packageItemRepository;
    @Autowired
    DiscountRepository discountRepository;

    @Override
    public boolean addRestaurant(int rid, String name, String password, String location, String region, int owner, String photo, String certificate, String kind) {
        int k;
        switch (kind){
            case "中餐":
                k=1;
                break;
            case "西餐":
                k=2;
                break;
            case "快餐":
                k=3;
                break;
            case "甜品":
                k=4;
                break;
            case "小吃":
                k=5;
                break;
            case "其他":
                k=6;
                break;
            default:
                return false;
        }
        try{
            Restaurant restaurant=null;
            AddressServiceImpl a=new AddressServiceImpl();
            String loc=a.getLocation(location);
            String[] locs=loc.split(",");
            if(rid<0){

                restaurant=new Restaurant(name, password, "", location, region, owner, photo, certificate, false, k,Double.parseDouble(locs[0]),Double.parseDouble(locs[1]));
            }else{
                restaurant=new Restaurant(rid, name, password, "", location, region, owner, photo, certificate, false, k,Double.parseDouble(locs[0]),Double.parseDouble(locs[1]));
            }
            restaurantRepository.save(restaurant);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getApplyState(int owner) {
        Restaurant restaurant=restaurantRepository.findFirstByOwner(owner);
        if(restaurant==null){
            return 0;
        }else if(restaurant.getIfValid()){
            return 3;
        }else if(restaurant.getIdCode().equals("")){
            return 1;
        }else{
            return 2;
        }
    }

    @Override
    public Map<String, Object> getInfo(int owner) {
        Restaurant restaurant=restaurantRepository.findFirstByOwner(owner);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("rid", restaurant.getRid());
        resultMap.put("kind", restaurant.getKind());
        resultMap.put("name", restaurant.getName());
        resultMap.put("password", restaurant.getPassword());
        resultMap.put("photoSrc", restaurant.getPhoto());
        resultMap.put("certificateSrc", restaurant.getCertificate());
        resultMap.put("location", restaurant.getLocation());
        resultMap.put("region", restaurant.getRegion());

        return resultMap;
    }

    @Override
    public Restaurant[] getApplications() {
        List<Restaurant> list=restaurantRepository.findAllByIfValidAndIdCode(false, "");
        int length=list.size();
        Restaurant[] applications=new Restaurant[length];
        for(int i=0;i<length;i++){
            applications[i]=list.get(i);
        }
        return applications;
    }

    @Override
    public Restaurant getApplicationDetail(int applicationId) {
        return restaurantRepository.findFirstByRid(applicationId);
    }

    @Override
    public boolean pass(int applicationId) {
        Restaurant restaurant=restaurantRepository.findFirstByRid(applicationId);
        setIdCode(restaurant);
        restaurant.setIfValid(true);
        restaurantRepository.save(restaurant);
        return true;
    }

    @Override
    public boolean veto(int applicationId) {
        Restaurant restaurant=restaurantRepository.findFirstByRid(applicationId);
        setIdCode(restaurant);
        restaurantRepository.save(restaurant);
        return true;
    }

    @Override
    public String getIdCode(int owner) {
        return restaurantRepository.findFirstByOwner(owner).getIdCode();
    }

    @Override
    public String getName(int rid) {
        return restaurantRepository.findFirstByRid(rid).getName();
    }

    @Override
    public Restaurant[] getRestaurantList() {
        List<Restaurant> list=restaurantRepository.findByIfValid(true);
        int length=list.size();
        Restaurant[] restaurants=new Restaurant[length];
        for(int i=0;i<length;i++){
            restaurants[i]=list.get(i);
        }
        return restaurants;
    }

    @Override
    public RestaurantDetail getRestaurantDetailByUser(int rid) {
        Restaurant restaurant=restaurantRepository.findFirstByRid(rid);
        List<Commodity> clist1=commodityRepository.findByRid(rid);
        List<Commodity> clist2=new ArrayList<>();
        for(Commodity c:clist1){
                clist2.add(c);
        }
        int clength=clist2.size();
        Commodity[] commodities=new Commodity[clength];
        for(int i=0;i<clength;i++){
            commodities[i]=clist2.get(i);
        }

        List<Discount> dlist1=discountRepository.findByRid(rid);
        List<Discount> dlist2=new ArrayList<>();
        for(Discount d:dlist1){
                dlist2.add(d);
        }
        int dlength=dlist2.size();
        Discount[] discounts=new Discount[dlength];
        for(int i=0;i<dlength;i++){
            discounts[i]=dlist2.get(i);
        }

        List<Package> plist1 = packageRepository.findByRid(rid);
        List<Package> plist2 = new ArrayList<>();
        for(Package p:plist1){
                plist2.add(p);
        }

        int plength=plist2.size();
        System.out.println(plength);
        PackageDetail[] packages=new PackageDetail[plength];
        for(int i=0;i<plength;i++){
            Package aPackage=plist2.get(i);
            packages[i]=new PackageDetail(aPackage.getPid(), aPackage.getRid(), aPackage.getName(), aPackage.getSold(), aPackage.getPrice(), aPackage.getDescription(), aPackage.getState(), aPackage.getIfValid(), getPackageItems(aPackage.getPid()));
        }

        RestaurantDetail restaurantDetail=new RestaurantDetail(restaurant.getRid(), restaurant.getName(), restaurant.getLocation(), restaurant.getRegion(), restaurant.getPhoto(), restaurant.getKind(), commodities, packages, discounts);

        return restaurantDetail;
    }

    @Override
    public List<String> getRestaurantAddresses() {
        List<Restaurant> list=restaurantRepository.findAll();
        List<String> addresses=new ArrayList<>();
        for(Restaurant restaurant:list){
            addresses.add(restaurant.getLocation());
        }
        return addresses;
    }

    @Override
    public Restaurant modifyRestaurant(Restaurant restaurant) {
        return restaurantRepository.saveAndFlush(restaurant);
    }

    private void setIdCode(Restaurant restaurant){
        String idCode=""+restaurant.getRid();
        int length=idCode.length();
        for(int i=0;i<6-length;i++){
            idCode="0"+idCode;
        }
        idCode="1"+idCode;
        restaurant.setIdCode(idCode);
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
}
