package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.CommodityRepository;
import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service("PackageService")
public class PackageServiceImpl implements PackageService {
    @Autowired
    CommodityRepository commodityRepository;

    @Override
    public Commodity[] getOptions(int rid, LocalDate beginDate, LocalDate endDate) {
        List<Commodity> commodities=commodityRepository.findByRid(rid);
        List<Commodity> items=new ArrayList<>();
        for(int i=0;i<commodities.size();i++){
            if(!(commodities.get(i).getBeginDate().isAfter(beginDate)||commodities.get(i).getEndDate().isBefore(endDate))){
                items.add(commodities.get(i));
            }
        }
        int length=items.size();
        Commodity[] options=new Commodity[length];
        for(int i=0;i<length;i++){
            options[i]=items.get(i);
        }
        return options;
    }
}
