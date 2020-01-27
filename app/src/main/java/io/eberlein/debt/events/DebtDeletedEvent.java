package io.eberlein.debt.events;

import io.eberlein.debt.objects.Debt;

public class DebtDeletedEvent {
    private Debt debt;

    public DebtDeletedEvent(Debt p) {
        debt = p;
    }

    public Debt getDebt() {
        return debt;
    }
}