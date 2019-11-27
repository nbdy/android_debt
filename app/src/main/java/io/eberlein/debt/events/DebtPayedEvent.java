package io.eberlein.debt.events;

import io.eberlein.debt.Debt;

public class DebtPayedEvent {
    private Debt debt;

    public DebtPayedEvent(Debt p) {
        debt = p;
    }

    public Debt getDebt() {
        return debt;
    }
}
