package com.springboot.yummy.controller;

import com.springboot.yummy.entity.Modification;
import com.springboot.yummy.service.ModificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/modification")
public class ModificationController {
    private final ModificationService modificationService;

    @Autowired  //自动装配
    public ModificationController(ModificationService modificationService) {
        this.modificationService=modificationService;
    }

    @RequestMapping(value = "/hasModification", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean hasModification(@RequestBody Map<String, Object> requestMap){
        int rid= Integer.parseInt((String)requestMap.get("rid"));
        return modificationService.hasModification(rid);
    }

    @RequestMapping(value = "/apply", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean apply(@RequestBody Map<String, Object> requestMap){
        int rid= (Integer)requestMap.get("rid");
        String name=requestMap.get("name").toString();
        String kind=requestMap.get("kind").toString();
        String password=requestMap.get("password").toString();
        String location=requestMap.get("location").toString();
        String region=requestMap.get("region").toString();
        String certificate=requestMap.get("certificate").toString();
        String photo=requestMap.get("photo").toString();
        return modificationService.addModification(name, password, location, region, rid, photo, certificate, kind);
    }

    @RequestMapping(value = "/getModifications", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Modification[] getModifications(){
        return modificationService.getModifications();
    }

    @RequestMapping(value = "/getModificationDetail", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public Modification getModificationDetail(@RequestBody Map<String, Object> requestMap){
        int mid= Integer.parseInt((String)requestMap.get("mid"));
        return modificationService.getModificationDetail(mid);
    }

    @RequestMapping(value = "/pass", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean pass(@RequestBody Map<String, Object> requestMap){
        int mid= Integer.parseInt((String)requestMap.get("mid"));
        return modificationService.pass(mid);
    }

    @RequestMapping(value = "/veto", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public boolean veto(@RequestBody Map<String, Object> requestMap){
        int mid= Integer.parseInt((String)requestMap.get("mid"));
        return modificationService.veto(mid);
    }
}
