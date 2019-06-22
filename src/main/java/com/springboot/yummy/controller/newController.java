package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.PackageDetail;
import com.springboot.yummy.entity.Restaurant;
import com.springboot.yummy.service.AddressService;
import com.springboot.yummy.service.CommodityService;
import com.springboot.yummy.service.PackageService;
import com.springboot.yummy.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author:Wang Mo
 * @Description：
 */
@Transactional
@RestController
public class newController {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    AddressService addressService;
    @Autowired
    CommodityService commodityService;
    @Autowired
    PackageService packageService;

    @RequestMapping(value="/restaurant/getRestaurantList", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Restaurant[] getRestaurantList(@RequestBody Map<String,Object> requestMap){
        String cusAddress=requestMap.get("location").toString();
        int rKind=Integer.parseInt(requestMap.get("rKind").toString());
        String search=requestMap.get("searchContent").toString();
        Restaurant[] rests=restaurantService.getRestaurantList();
        List<Restaurant> resultRestaurant1=new ArrayList<>();
        for(Restaurant rest:rests){
            int time=addressService.canConvey(rest.getLocation(),cusAddress);
            if(time!=-1){
                resultRestaurant1.add(rest);
            }
        }
        if(rKind!=0){
            for(int i=0;i<resultRestaurant1.size();i++){
                if(resultRestaurant1.get(i).getKind()!=rKind){
                    resultRestaurant1.remove(i);
                    i--;
                }
            }
        }
        List<Restaurant> resultRestaurant2=new ArrayList<>();
        if(!search.equals("")){
            for(int i=0;i<resultRestaurant1.size();i++){
                Restaurant restaurant=resultRestaurant1.get(i);
                if(restaurant.getName().contains(search)){
                    resultRestaurant2.add(restaurant);
                    continue;
                }
                boolean contain=false;
                Commodity[] commodities=commodityService.getCommodities(restaurant.getRid());
                for(Commodity commodity:commodities){
                    if(commodity.getState().equals("销售中")&&(commodity.getName().contains(search)||commodity.getDescription().contains(search))){
                        contain=true;
                        break;
                    }
                }
                if(contain){
                    resultRestaurant2.add(restaurant);
                    continue;
                }

                PackageDetail[] packageDetails=packageService.getPackages(restaurant.getRid());
                for(PackageDetail packageDetail:packageDetails){
                    if(packageDetail.getState().equals("销售中")&&(packageDetail.getName().contains(search)||packageDetail.getDescription().contains(search))){
                        contain=true;
                        break;
                    }
                }
                if(contain){
                    resultRestaurant2.add(restaurant);
                    continue;
                }

            }
        }
        Restaurant[] result=new Restaurant[resultRestaurant2.size()];
        for(int i=0;i<resultRestaurant2.size();i++){
            result[i]=resultRestaurant2.get(i);
        }
        return result;
    }

}
