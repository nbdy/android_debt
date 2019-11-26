package io.eberlein.debt.events;

import io.eberlein.debt.Profit;

public class ProfitDeletedEvent {
    private Profit profit;

    public ProfitDeletedEvent(Profit p){
        profit = p;
    }

    public Profit getProfit() {
        return profit;
    }
}