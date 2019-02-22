package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.*;
import com.springboot.yummy.entity.*;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.service.OrderService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
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
    OrderCommodityRepository orderCommodityRepository;
    @Autowired
    OrderPackageRepository orderPackageRepository;
    @Autowired
    CommodityRepository commodityRepository;
    @Autowired
    PackageRepository packageRepository;
    @Autowired
    PackageItemRepository packageItemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    DiscountRepository discountRepository;

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

            double discount=getHighestDiscount(uid, rid, total);

            Order order=new Order(uid, rid, total, discount, total-discount, 0, target, 0, LocalDateTime.now(), null, null, null);
            int oid=orderRepository.save(order).getOid();

            for (int i=0;i<cids.size();i++){
                Commodity commodity=commodityRepository.findFirstByCid(cids.get(i));
                double price=commodity.getPrice();
                String name=commodity.getName();
                String kind=commodity.getKind();
                OrderCommodity oc=new OrderCommodity(oid, cids.get(i), cnums.get(i), name, price, kind);
                orderCommodityRepository.save(oc);
            }

            for (int i=0;i<pids.size();i++){
                Package aPackage =packageRepository.findFirstByPid(pids.get(i));
                double price=aPackage.getPrice();
                String name=aPackage.getName();
                OrderPackage op=new OrderPackage(oid, pids.get(i), pnums.get(i), name, price);
                orderPackageRepository.save(op);
            }

            return oid;
        }catch (Exception e){
            e.printStackTrace();
            return -3;
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

    private double getHighestDiscount(int uid, int rid, double total){
        double discount=0;
        int level=userRepository.findFirstByUid(uid).getLevel();
        switch (level){
            case 0:
                discount=0;
                break;
            case 1:
                discount=0.1*total;
                break;
            case 2:
                discount=0.2*total;
                break;
            case 3:
                discount=0.3*total;
                break;
        }

        List<Discount> list1=discountRepository.findByRid(rid);
        List<Discount> list2=new ArrayList<>();
        for(Discount d:list1){
            if(!(d.getEndDate().isBefore(LocalDate.now())||d.getBeginDate().isAfter(LocalDate.now()))){
                list2.add(d);
            }
        }
        for(Discount d:list2){
            if(total>=d.getTotal()&&discount<d.getDiscount()){
                discount=d.getDiscount();
            }
        }
        return discount;
    }


}
