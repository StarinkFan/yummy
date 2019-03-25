package com.springboot.yummy.service.Impl;

import com.springboot.yummy.dao.BankAccountRepository;
import com.springboot.yummy.entity.BankAccount;
import com.springboot.yummy.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BankAccountService")
public class BankAccountServiceImpl implements BankAccountService {
    @Autowired
    BankAccountRepository bankAccountRepository;

    @Override
    public int pay(String account, String password, double shouldPay) {
        BankAccount bankAccount=bankAccountRepository.findFirstByAccountAndPassword(account, password);
        if(bankAccount==null){
            return -1;
        }else if(bankAccount.getDeposit()<shouldPay){
            return -2;
        }else {
            bankAccount.setDeposit(bankAccount.getDeposit()-shouldPay);
            bankAccountRepository.save(bankAccount);
            return 1;
        }
    }
}
