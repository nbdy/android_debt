package io.eberlein.debt.settings;

import io.eberlein.debt.ui.PaymentSettingsFragment;

public class PaymentSetting extends Setting {
    public PaymentSetting(){
        fragment = new PaymentSettingsFragment();
        name = "payment";
    }
}
