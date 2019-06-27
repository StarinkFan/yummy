package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.CommodityCartRepository;
import com.springboot.yummy.dao.PackageCartRepository;
import com.springboot.yummy.entity.CommodityCart;
import com.springboot.yummy.entity.PackageCart;
import com.springboot.yummy.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:Wang Mo
 * @Description：
 */
@Service("CartService")
public class CartServiceImpl implements CartService{
    @Autowired
    CommodityCartRepository commodityCartRepository;
    @Autowired
    PackageCartRepository packageCartRepository;


    @Override
    public boolean changeCommodityCart(int uid, int rid, int cid, int num) {
        CommodityCart c=commodityCartRepository.findByCidAndRidAndUid(cid,rid,uid);
        if(c!=null){
            c.setNum(num);
            commodityCartRepository.saveAndFlush(c);
            return true;
        }
        CommodityCart commodityCart=new CommodityCart();
        commodityCart.setCid(cid);
        commodityCart.setNum(num);
        commodityCart.setRid(rid);
        commodityCart.setUid(uid);
        commodityCartRepository.save(commodityCart);
        return true;
    }

    @Override
    public boolean changePackageCart(int uid, int rid, int pid, int num) {
        PackageCart p=packageCartRepository.findByPidAndRidAndUid(pid,rid,uid);
        if(p!=null){
            p.setNum(num);
            packageCartRepository.saveAndFlush(p);
            return true;
        }
        PackageCart packageCart=new PackageCart();
        packageCart.setNum(num);
        packageCart.setPid(pid);
        packageCart.setRid(rid);
        packageCart.setUid(uid);
        packageCartRepository.save(packageCart);
        return true;
    }
}
