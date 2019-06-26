package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.PackageDetail;
import com.springboot.yummy.entity.Restaurant;
import com.springboot.yummy.service.AddressService;
import com.springboot.yummy.service.CommodityService;
import com.springboot.yummy.service.PackageService;
import com.springboot.yummy.service.RestaurantService;
import com.springboot.yummy.vo.RestaurantVO;
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
    public RestaurantVO[] getRestaurantList(@RequestBody Map<String,Object> requestMap){
        String cusAddress=requestMap.get("location").toString();
        int rKind=Integer.parseInt(requestMap.get("rKind").toString());
        String search=requestMap.get("searchContent").toString();
        Restaurant[] rests=restaurantService.getRestaurantList();
        List<RestaurantVO> resultRestaurant1=new ArrayList<>();
        String[] cusLocs=addressService.getLocation(cusAddress).split(",");
        double cusLat=Double.parseDouble(cusLocs[0]);
        double cusLng=Double.parseDouble(cusLocs[1]);
        for(Restaurant rest:rests){
            double radLat1 = rad(cusLat);
            double radLat2 = rad(rest.getLat());
            double a = radLat1 - radLat2;
            double b = rad(cusLng) - rad(rest.getLng());
            double s = 2 * Math.asin(Math.sqrt(Math.abs(Math.pow(Math.sin(a/2),2) +
                    Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))));
            s = s * 6371.393;
            s = Math.round(s * 1000);
            int time= (int) (25+s/240);
            if(time<=45){
                RestaurantVO restaurantVO=new RestaurantVO();
                restaurantVO.setKind(rest.getKind());
                restaurantVO.setLocation(rest.getLocation());
                restaurantVO.setName(rest.getName());
                restaurantVO.setPhoto(rest.getPhoto());
                restaurantVO.setRegion(rest.getRegion());
                restaurantVO.setRid(rest.getRid());
                restaurantVO.setTime(time);
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
        List<RestaurantVO> resultRestaurant2=new ArrayList<>();
        if(!search.equals("")){
            for(int i=0;i<resultRestaurant1.size();i++){
                RestaurantVO restaurant=resultRestaurant1.get(i);
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
        RestaurantVO[] result=new RestaurantVO[resultRestaurant2.size()];
        for(int i=0;i<resultRestaurant2.size();i++){
            result[i]=resultRestaurant2.get(i);
        }
        return result;
    }

    @RequestMapping("/abccc")
    @ResponseBody
    public void change(){
        Restaurant[] restaurants=restaurantService.getRestaurantList();
        for(Restaurant restaurant:restaurants){
            String[] locs=addressService.getLocation(restaurant.getLocation()).split(",");
            restaurant.setLat(Double.parseDouble(locs[0]));
            restaurant.setLng(Double.parseDouble(locs[1]));
            restaurantService.modifyRestaurant(restaurant);
        }
    }

    private static double rad(double d)
    {
        return d * Math.PI / 180.0;
    }
}
