package com.springboot.yummy.controller;

import com.springboot.yummy.entity.BankAccount;
import com.springboot.yummy.service.BankAccountService;
import com.springboot.yummy.service.OrderService;
import com.springboot.yummy.util.UnconfirmedOrdersMonitor;
import com.springboot.yummy.util.UnpaidOrdersMonitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final OrderService orderService;

    @Autowired  //自动装配
    public BankAccountController(BankAccountService bankAccountService,OrderService orderService) {
        this.bankAccountService=bankAccountService;
        this.orderService=orderService;
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST, headers = "Accept=application/json")
    @ResponseBody
    public int login(@RequestBody Map<String, Object> requestMap){
        System.out.println("pay");
        int oid= (Integer)requestMap.get("oid");
        String account=requestMap.get("account").toString();
        String password=requestMap.get("password").toString();
        Double shouldPay = Double.parseDouble(requestMap.get("shouldPay").toString());
        System.out.println(account+" "+password);
        int result=bankAccountService.pay(account, password, shouldPay,oid);
        if(result>0){
            try {
                orderService.setState(oid, 1, result);
                UnconfirmedOrdersMonitor.addUnconfirmedOrder(oid);
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }
        return result;
    }

}
