package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.ModificationRepository;
import com.springboot.yummy.entity.Modification;
import com.springboot.yummy.service.ModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ModificationService")
public class ModificationServiceImpl implements ModificationService {
    @Autowired
    ModificationRepository modificationRepository;

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
}
