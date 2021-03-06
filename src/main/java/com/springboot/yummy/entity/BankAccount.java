package com.springboot.yummy.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "bankAccounts")
public class BankAccount implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int baid;
    private String account;
    private String password;
    @Column(columnDefinition="float(10,2) default '0.00'")
    private double deposit;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public int getBaid() {
        return baid;
    }

    public void setBaid(int baid) {
        this.baid = baid;
    }
}
