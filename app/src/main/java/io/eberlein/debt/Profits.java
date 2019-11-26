package io.eberlein.debt;

import java.util.ArrayList;
import java.util.List;

public class Profits {
    private List<Profit> profits;

    Profits(){
        profits = new ArrayList<>();
    }

    public List<Profit> getProfits() {
        return profits;
    }

    Profit add(double amount, String why){
        Profit p = new Profit(amount, why);
        profits.add(p);
        return p;
    }

    Profit get(int i){
        return profits.get(i);
    }

    void add(Profit p){
        profits.add(p);
    }

    void remove(Profit p){
        profits.remove(p);
    }

    int size(){
        return profits.size();
    }

    double getSum(){
        double s = 0;
        for(Profit p : profits)
            s += p.getAmount();
        return s;
    }
}
