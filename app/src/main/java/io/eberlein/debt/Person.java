package io.eberlein.debt;

import java.util.UUID;

import io.paperdb.Paper;

public class Person {
    private String key;
    private String name;
    private String from;
    private Debts debts;

    public Person(){
        key = UUID.randomUUID().toString();
        debts = new Debts();
    }

    public Person(String name){
        key = UUID.randomUUID().toString();
        this.name = name;
        debts = new Debts();
    }

    public Person(String name, String from){
        key = UUID.randomUUID().toString();
        this.name = name;
        this.from = from;
        debts = new Debts();
    }

    public String getName() {
        return name;
    }

    public String getFrom() {
        return from;
    }

    public String getKey() {
        return key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Debt addProfit(double amount, String why) {
        return debts.add(amount, why);
    }

    public void removeProfit(Debt p) {
        debts.remove(p);
    }

    public Debts getDebts() {
        return debts;
    }

    public double getDebt(){
        return debts.getSum();
    }

    public void save(){
        Paper.book(Static.BOOK_PERSONS).write(key, this);
    }

    public void setDebtPayed(Debt d) {
        debts.setDebtPayed(d);
    }
}
