package io.eberlein.debt;

import java.util.Date;

public class Debt {
    private double amount;
    private String why;
    private Date timestamp;
    private boolean payed;

    public Debt() {
    }

    public Debt(double amount) {
        this.amount = amount;
        timestamp = new Date();
    }

    public Debt(double amount, String why) {
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

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
