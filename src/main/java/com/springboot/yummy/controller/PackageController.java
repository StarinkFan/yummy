package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.entity.PackageItem;
import com.springboot.yummy.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Transactional
@RestController
@RequestMapping("/package")
public class PackageController {
    private final PackageService packageService;

    @Autowired  //自动装配
    public PackageController(PackageService packageService) {
        this.packageService=packageService;
    }

    @RequestMapping(value = "/getOptions", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Commodity[] getOptions(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        LocalDate beginDate=LocalDate.parse(requestMap.get("beginDate").toString());
        LocalDate endDate=LocalDate.parse(requestMap.get("endDate").toString());
        return packageService.getOptions(rid, beginDate, endDate);
    }

    @RequestMapping(value = "/addPackage", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean addPackage(@RequestBody Map<String, Object> requestMap){
        return packageService.savePackage(requestMap);
    }

    @RequestMapping(value = "/getPackages", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Package[] getPackages(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        return packageService.getPackages(rid);
    }

    @RequestMapping(value = "/getPackageItems", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public PackageItem[] getPackageItems(@RequestBody Map<String, Object> requestMap){
        int pid= Integer.parseInt((String)requestMap.get("pid"));
        return packageService.getPackageItems(pid);
    }

}
