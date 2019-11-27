package io.eberlein.debt;

import java.util.ArrayList;
import java.util.List;

public class Profits {
    private List<Profit> profits;

    public Profits() {
        profits = new ArrayList<>();
    }

    public List<Profit> getProfits() {
        return profits;
    }

    public Profit add(double amount, String why) {
        Profit p = new Profit(amount, why);
        profits.add(p);
        return p;
    }

    public Profit get(int i) {
        return profits.get(i);
    }

    public void add(Profit p) {
        profits.add(p);
    }

    public void remove(Profit p) {
        profits.remove(p);
    }

    public int size() {
        return profits.size();
    }

    public double getSum() {
        double s = 0;
        for(Profit p : profits)
            s += p.getAmount();
        return s;
    }
}
