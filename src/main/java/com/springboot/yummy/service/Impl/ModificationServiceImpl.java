package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.ModificationRepository;
import com.springboot.yummy.dao.RestaurantRepository;
import com.springboot.yummy.entity.Modification;
import com.springboot.yummy.entity.Restaurant;
import com.springboot.yummy.service.ModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ModificationService")
public class ModificationServiceImpl implements ModificationService {
    @Autowired
    ModificationRepository modificationRepository;
    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    public boolean hasModification(int rid) {
        Modification modification=modificationRepository.findFirstByRid(rid);
        if(modification==null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean addModification(String name, String password, String location, String region, int rid, String photo, String certificate, String kind) {
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
            Modification modification=new Modification(name, password, location, region, rid, photo, certificate, k);
            modificationRepository.save(modification);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Modification[] getModifications() {
        List<Modification> list=modificationRepository.findAll();
        int length=list.size();
        Modification[] modifications=new Modification[length];
        for(int i=0;i<length;i++){
            modifications[i]=list.get(i);
        }
        return modifications;
    }

    @Override
    public Modification getModificationDetail(int mid) {
        return modificationRepository.findFirstByMid(mid);
    }

    @Override
    public boolean pass(int mid) {
        try{
            Modification modification=modificationRepository.findFirstByMid(mid);
            Restaurant restaurant=restaurantRepository.findFirstByRid(modification.getRid());

            restaurant.setName(modification.getName());
            restaurant.setPassword(modification.getPassword());
            restaurant.setKind(modification.getKind());
            restaurant.setLocation(modification.getLocation());
            restaurant.setRegion(modification.getRegion());
            restaurant.setPhoto(modification.getPhoto());
            restaurant.setCertificate(modification.getCertificate());

            restaurantRepository.save(restaurant);
            modificationRepository.delete(modification);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean veto(int mid) {
        try{
            modificationRepository.delete(modificationRepository.findFirstByMid(mid));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
