package com.springboot.yummy.dao;

import com.springboot.yummy.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    BankAccount findFirstByAccountAndPassword(String account, String password);

    BankAccount findFirstByBaid(int baid);
}
