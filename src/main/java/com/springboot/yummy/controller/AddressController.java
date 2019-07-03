package com.springboot.yummy.controller;

import com.springboot.yummy.service.AddressService;
import com.springboot.yummy.vo.CanConveyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;

    @Autowired  //自动装配
    public AddressController(AddressService addressService) {
        this.addressService=addressService;
    }

    @RequestMapping(value = "/getSimilarLocations", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public String[] getSimilarLocations(@RequestBody Map<String, Object> requestMap){
        String query = requestMap.get("keyword").toString();
        String region = requestMap.get("area").toString();
        return addressService.getSimilarLocations(query, region);
    }

    @RequestMapping(value = "/canConvey", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public CanConveyVO canConvey(@RequestBody Map<String, Object> requestMap){
        String departure = requestMap.get("departure").toString();
        String target = requestMap.get("target").toString();
        double[] distanceAndTime=addressService.getDistanceAndTime(departure, target);
        CanConveyVO canConveyVO=new CanConveyVO();
        canConveyVO.setDistance(distanceAndTime[0]);
        canConveyVO.setTime((int) distanceAndTime[1]);
        return canConveyVO;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean addTargets(@RequestBody Map<String,Object> requstMap){
        int uid=Integer.parseInt(requstMap.get("uid").toString());
        String location=requstMap.get("location").toString();
        String region=requstMap.get("region").toString();
        System.out.println("location:"+location);
        System.out.println("region:"+region);
        addressService.addUserTarget(uid,location,region);
        return true;
    }
}
