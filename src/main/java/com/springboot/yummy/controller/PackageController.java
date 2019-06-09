package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.entity.PackageDetail;
import com.springboot.yummy.entity.PackageItem;
import com.springboot.yummy.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
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
    public List<Commodity> getOptions(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        return packageService.getOptions(rid);
    }

    @RequestMapping(value = "/addPackage", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean addPackage(@RequestBody Map<String, Object> requestMap){
        return packageService.savePackage(requestMap);
    }

    @RequestMapping(value = "/getPackages", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public PackageDetail[] getPackages(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        return packageService.getPackages(rid);
    }

    @RequestMapping(value = "/deletePackage", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean deletePackage(@RequestBody Map<String, Object> requestMap){
        int pid= (Integer)requestMap.get("pid");
        return packageService.deletePackage(pid);
    }

    @RequestMapping(value = "/hasSameName", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean hasSameName(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        int pid= (Integer)requestMap.get("pid");
        String name= (String) requestMap.get("name");
        return packageService.hasSameName(rid, pid, name);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean validate(@RequestBody Map<String, Object> requestMap){
        int pid= (Integer)requestMap.get("pid");
        return packageService.validate(pid);
    }

    @RequestMapping(value = "/invalidate", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean invalidate(@RequestBody Map<String, Object> requestMap){
        int pid= (Integer)requestMap.get("pid");
        return packageService.invalidate(pid);
    }
}
