package io.eberlein.debt;

import java.util.ArrayList;
import java.util.List;

public class Debts {
    private List<Debt> debts;

    public Debts() {
        debts = new ArrayList<>();
    }

    public List<Debt> getDebts() {
        return debts;
    }

    public Debt add(double amount, String why) {
        Debt p = new Debt(amount, why);
        debts.add(p);
        return p;
    }

    public Debt get(int i) {
        return debts.get(i);
    }

    public void add(Debt p) {
        debts.add(p);
    }

    public void remove(Debt p) {
        debts.remove(p);
    }

    public int size() {
        return debts.size();
    }

    public double getSum() {
        double s = 0;
        for (Debt p : debts)
            s += p.getAmount();
        return s;
    }

}
