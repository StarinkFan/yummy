package com.springboot.yummy.entity;

import javax.persistence.*;

@Entity
@Table(name = "rules")
public class Rule {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private int ruid;
    private int top;
    private int percent;

    public Rule(){}

    public Rule(int top, int percent){
        this.top=top;
        this.percent=percent;
    }


    public int getRuid() {
        return ruid;
    }

    public void setRuid(int ruid) {
        this.ruid = ruid;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }
}
