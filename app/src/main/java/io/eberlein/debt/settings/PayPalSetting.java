package io.eberlein.debt.settings;

import io.eberlein.debt.ui.PaypalSettingsFragment;

public class PayPalSetting extends Setting {
    public PayPalSetting(){
        fragment = new PaypalSettingsFragment();
        name = "paypal";
    }
}
