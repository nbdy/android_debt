package io.eberlein.debt;

import java.util.UUID;

import io.paperdb.Paper;

public class Person {
    private String key;
    private String name;
    private String from;
    private Profits profits;

    public Person(){
        key = UUID.randomUUID().toString();
        profits = new Profits();
    }

    public Person(String name){
        key = UUID.randomUUID().toString();
        this.name = name;
        profits = new Profits();
    }

    public Person(String name, String from){
        key = UUID.randomUUID().toString();
        this.name = name;
        this.from = from;
        profits = new Profits();
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

    public Profit addProfit(double amount, String why){
        return profits.add(amount, why);
    }

    public void removeProfit(Profit p){
        profits.remove(p);
    }

    public Profits getProfits(){
        return profits;
    }

    public double getDebt(){
        return profits.getSum();
    }

    public void save(){
        Paper.book(Static.BOOK_PERSONS).write(key, this);
    }
}
