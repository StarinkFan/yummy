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

    @Override
    public int canConvey(String departure, String target) {
        String origin=getLocation(departure);
        String destination=getLocation(target);
        ArrayList<Double> distances=new ArrayList<>();
        ArrayList<Double> durations=new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        params.put("origin", origin);
        params.put("destination", destination);
        params.put("riding_type", 1);
        params.put("ak", "yvzxVkAsNqEcTQgkhmfRyaD3n51t0kd2");
        params.put("output", "json");
        String str=null;
        try {
            str = httpAPIService.doGet("http://api.map.baidu.com/direction/v2/riding", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
        JSONObject responseBody = new JSONObject(str);
        JSONArray array=responseBody.getJSONObject("result").getJSONArray("routes");
        for(int i=0; i<array.length(); i++) {
            JSONObject obj = array.getJSONObject(i);
            double distance=obj.getDouble("distance")/1000;
            double duration=obj.getDouble("duration")/60;
            distances.add(distance);
            durations.add(duration);
            if(distance<8||duration<30){
                System.out.println(duration);
                System.out.println((int)duration);
                return (int)duration;
            }
        }
        System.out.println(distances.toString());
        System.out.println(durations.toString());
        return -1;

    }

    private String getLocation(String address){
        String str=null;
        Map<String, Object> params = new HashMap<>();
        params.put("address", address);
        params.put("ak", "yvzxVkAsNqEcTQgkhmfRyaD3n51t0kd2");
        params.put("output", "json");
        try {
            str = httpAPIService.doGet("http://api.map.baidu.com/geocoder/v2/", params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
        JSONObject responseBody = new JSONObject(str);
        double lat=responseBody.getJSONObject("result").getJSONObject("location").getDouble("lat");
        double lng=responseBody.getJSONObject("result").getJSONObject("location").getDouble("lng");
        String result=lat+","+lng;
        System.out.println(result);
        return result;
    }
}
