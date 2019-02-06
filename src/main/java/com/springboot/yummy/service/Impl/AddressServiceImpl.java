package com.springboot.yummy.service.Impl;

import com.springboot.yummy.service.AddressService;
import com.springboot.yummy.util.HttpAPIService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("AddressService")
public class AddressServiceImpl implements AddressService {
    @Resource
    private HttpAPIService httpAPIService;

    @Override
    public String[] getSimilarLocations(String query, String region) {
        String[] locations=null;
        List<String> list= new ArrayList<>();
        String str=null;
        Map<String, Object> params = new HashMap<>();
        params.put("query", query);
        params.put("region",region);
        params.put("city_limit","true");
        params.put("ak", "yvzxVkAsNqEcTQgkhmfRyaD3n51t0kd2");
        params.put("output", "json");
        try {
            str = httpAPIService.doGet("http://api.map.baidu.com/place/v2/suggestion", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
        JSONObject responseBody = new JSONObject(str);
        JSONArray array=responseBody.getJSONArray("result");
        for(int i=0; i<array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            String name=obj.getString("name");
            System.out.println(name);
            list.add(name);
        }
        int length=list.size();
        locations=new String[length];
        for (int i=0;i<length;i++){
            locations[i]=list.get(i);
        }

        return locations;
    }
}
