package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.*;
import com.springboot.yummy.entity.*;
import com.springboot.yummy.entity.Package;
import com.springboot.yummy.service.OrderService;
import com.springboot.yummy.util.UnpaidOrdersMonitor;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service("OrderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RestaurantRepository restaurantRepository;
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
    DiscountRepository discountRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;


    @Override
    public int placeOrder(Map<String, Object> map) {
        ArrayList<Integer> cids=new ArrayList<>();
        ArrayList<Integer> cnums=new ArrayList<>();
        ArrayList<Integer> pids=new ArrayList<>();
        ArrayList<Integer> pnums=new ArrayList<>();

        try{
            int uid= Integer.parseInt((String)map.get("uid"));
            int rid= (Integer)map.get("rid");
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

            Order order=new Order(uid, rid, total, discount, total-discount, 0, target, 0, LocalDateTime.now(),
                    null, null, null, userRepository.findFirstByUid(uid).getName(), restaurantRepository.findFirstByRid(rid).getName());
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

            UnpaidOrdersMonitor.addUnpaidOrder(oid);

            updateCommodities( new ArrayList<>(cids), new ArrayList<>(cnums), new ArrayList<>(pids), new ArrayList<>(pnums));

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

    private void updateCommodities(ArrayList<Integer> cids, ArrayList<Integer> cnums, ArrayList<Integer> pids, ArrayList<Integer> pnums){
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
            commodity.setSold(commodity.getSold()+cnums.get(i));
            commodityRepository.save(commodity);
        }
    }


    @Override
    public OrderDetail getOrderDetail(int oid) {
       Order order=orderRepository.findFirstByOid(oid);
       List<OrderCommodity> commodities= orderCommodityRepository.findByOid(oid);
       List<OrderPackage> list1=orderPackageRepository.findByOid(oid);
       List<PackageDetail> packages=new ArrayList<>();
       for(OrderPackage aPackage:list1){
           packages.add(new PackageDetail(aPackage.getPid(), order.getRid(), aPackage.getName(), 0,aPackage.getPrice(),  null,null,true, getPackageItems(aPackage.getPid())));
       }
       return new OrderDetail(order, commodities, packages);
    }


    private PackageItem[] getPackageItems(int pid) {
        List<PackageItem> list = packageItemRepository.findByPid(pid);
        int length=list.size();
        PackageItem[] packageItems=new PackageItem[length];
        for(int i=0;i<length;i++){
            packageItems[i]=list.get(i);
        }
        return packageItems;
    }



    @Transactional
    @Override
    public void deleteOrder(int oid) {
        orderRepository.deleteByOid(oid);
        List<OrderCommodity> commodityList=orderCommodityRepository.findByOid(oid);
        List<OrderPackage> packageList=orderPackageRepository.findByOid(oid);
        ArrayList<Integer> cids=new ArrayList<>();
        ArrayList<Integer> cnums=new ArrayList<>();
        ArrayList<Integer> pids=new ArrayList<>();
        ArrayList<Integer> pnums=new ArrayList<>();

        for(OrderCommodity commodity:commodityList){
            cids.add(commodity.getCid());
            cnums.add(commodity.getNum());
            orderCommodityRepository.delete(commodity);
        }

        for(OrderPackage orderPackage:packageList){
            pids.add(orderPackage.getPid());
            pnums.add(orderPackage.getNum());
            orderPackageRepository.delete(orderPackage);
        }
        restoreCommodities(cids, cnums, pids, pnums);
    }

    private void restoreCommodities(ArrayList<Integer> cids, ArrayList<Integer> cnums, ArrayList<Integer> pids, ArrayList<Integer> pnums){
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
            commodity.setSold(commodity.getSold()-cnums.get(i));
            commodityRepository.save(commodity);
        }
    }


    @Override
    public int setState(int oid, int state, int baid) {
        int result=0;
        Order order=orderRepository.findFirstByOid(oid);
        order.setState(state);
        if(state==1){
            order.setPayTime(LocalDateTime.now());
            order.setBankAccount(baid);
        }else if(state==2){
            order.setArrivalTime(LocalDateTime.now());
            result=1;
        }else if(state==3){
            order.setRefundTime(LocalDateTime.now());
            Duration duration=Duration.between(order.getPayTime(),order.getRefundTime());
            long minutes=duration.toMinutes();
            Double refund;
            if(minutes<2){
                refund=order.getPay();
                result=2;
            }else if(minutes<10){
                refund=(double) Math.round(order.getPay()*0.8 * 100) / 100;
                result=10;
            }else{
                refund=(double) Math.round(order.getPay()*0.5 * 100) / 100;
                result=20;
            }
            order.setRefund(refund);
            BankAccount bankAccount=bankAccountRepository.findFirstByBaid(order.getBankAccount());
            bankAccount.setDeposit(bankAccount.getDeposit()+refund);
            bankAccountRepository.save(bankAccount);
        }
        orderRepository.save(order);
        return result;
    }

    @Override
    public List<Order> getUserOrders(int uid) {
        return orderRepository.findByUid(uid);
    }

    @Override
    public List<Order> getRestaurantOrders(int rid) {
        return orderRepository.findByRid(rid);
    }
}
