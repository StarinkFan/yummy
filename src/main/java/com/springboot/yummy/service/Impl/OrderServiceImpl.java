package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.CommodityRepository;
import com.springboot.yummy.dao.OrderRepository;
import com.springboot.yummy.dao.PackageItemRepository;
import com.springboot.yummy.dao.PackageRepository;
import com.springboot.yummy.entity.Commodity;
import com.springboot.yummy.entity.Order;
import com.springboot.yummy.entity.PackageItem;
import com.springboot.yummy.service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CommodityRepository commodityRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    PackageItemRepository packageItemRepository;

    @Override
    public int placeOrder(Map<String, Object> map) {
        ArrayList<Integer> cids=new ArrayList<>();
        ArrayList<Integer> cnums=new ArrayList<>();
        ArrayList<Integer> pids=new ArrayList<>();
        ArrayList<Integer> pnums=new ArrayList<>();

        try{
            int uid= Integer.parseInt((String)map.get("uid"));
            int rid= Integer.parseInt((String)map.get("rid"));
            String target= (String)map.get("target");

            JSONArray commodities = JSONArray.fromObject(map.get("commodities"));
            Iterator<Object> it = commodities.iterator();
            while (it.hasNext()) {
                JSONObject ob = (JSONObject) it.next();
                int cid=ob.getInt("cid");
                int num=ob.getInt("num");
                if(num>0){
                    cids.add(cid);
                    cnums.add(num);
                }
            }

            JSONArray packages = JSONArray.fromObject(map.get("packages"));
            it = packages.iterator();
            while (it.hasNext()) {
                JSONObject ob = (JSONObject) it.next();
                int pid=ob.getInt("pid");
                int num=ob.getInt("num");
                if(num>0){
                    pids.add(pid);
                    pnums.add(num);
                }
            }

            if(pids.size()==0&&cids.size()==0){
                return -1;
            }

            if( !checkEnough( new ArrayList<>(cids), new ArrayList<>(cnums), new ArrayList<>(pids), new ArrayList<>(pnums) ) ){
                return -2;
            }

            double total=0;
            for(int i=0;i<cids.size();i++){
                total=total+commodityRepository.findFirstByCid(cids.get(i)).getPrice()*cnums.get(i);
            }
            for(int i=0;i<pids.size();i++){
                total=total+packageRepository.findFirstByPid(pids.get(i)).getPrice()*pnums.get(i);
            }

            double discount=0;
            //补充discount

            Order order=new Order(uid, rid, total, discount, total-discount, 0, target, 0, LocalDateTime.now(), null, null, null);

            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }


    private boolean checkEnough(ArrayList<Integer> cids, ArrayList<Integer> cnums, ArrayList<Integer> pids, ArrayList<Integer> pnums){
        for(int i=0;i<pids.size();i++){
            List<PackageItem> items=packageItemRepository.findByPid(pids.get(i));
            for(PackageItem item: items){
                int index=cids.indexOf(item.getCid());
                if(index>=0){
                    cnums.set(index, cnums.get(index)+item.getNum()*pnums.get(i));
                }else{
                    cids.add(item.getCid());
                    cnums.add(item.getNum()*pnums.get(i));
                }
            }
        }

        for (int i=0;i<cids.size();i++){
            Commodity commodity=commodityRepository.findFirstByCid(cids.get(i));
            if(commodity.getAmount()-commodity.getSold()<cnums.get(i)){
                return false;
            }
        }
        return true;
    }

}
