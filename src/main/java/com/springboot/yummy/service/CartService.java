package com.springboot.yummy.service;

/**
 * @Author:Wang Mo
 * @Description：
 */
public interface CartService {
    boolean changeCommodityCart(int uid,int rid,int cid,int num);
    boolean changePackageCart(int uid, int rid,int pid,int num);
}
