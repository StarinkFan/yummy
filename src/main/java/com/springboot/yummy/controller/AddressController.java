package com.springboot.yummy.controller;

import com.springboot.yummy.service.AddressService;
import com.springboot.yummy.service.UserService;
import com.springboot.yummy.util.HttpAPIService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Iterator;
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
    public String[] login(@RequestBody Map<String, Object> requestMap){
        String query = requestMap.get("keyword").toString();
        String region = requestMap.get("area").toString();
        return addressService.getSimilarLocations(query, region);
    }
}
