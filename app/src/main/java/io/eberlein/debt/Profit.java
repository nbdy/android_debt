package io.eberlein.debt;

import java.util.Date;

public class Profit {
    private double amount;
    private String why;
    private Date timestamp;

    public Profit(){}

    public Profit(double amount){
        this.amount = amount;
        timestamp = new Date();
    }

    public Profit(double amount, String why){
        this.amount = amount;
        this.why = why;
        timestamp = new Date();
    }

    public double getAmount() {
        return amount;
    }

    public String getWhy() {
        return why;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
