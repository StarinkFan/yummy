package com.springboot.yummy.service;

public interface BankAccountService {
    int pay(String account, String password, double shouldPay, int oid);
}
