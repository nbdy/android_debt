package io.eberlein.debt;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import io.eberlein.debt.ui.BitcoinSettingsFragment;
import io.eberlein.debt.ui.GeneralSettingsFragment;
import io.eberlein.debt.ui.PaymentSettingsFragment;
import io.eberlein.debt.ui.PaypalSettingsFragment;

class Setting {
    Fragment fragment;
    String name;

    Setting(){}

    public String getName() {
        return name;
    }

    public Fragment getFragment() {
        return fragment;
    }
}

class GeneralSetting extends Setting {
    GeneralSetting(){
        fragment = new GeneralSettingsFragment();
        name = "general";
    }
}

class PaymentSetting extends Setting {
    PaymentSetting(){
        fragment = new PaymentSettingsFragment();
        name = "payment";
    }
}

class PayPalSetting extends Setting {
    PayPalSetting(){
        fragment = new PaypalSettingsFragment();
        name = "paypal";
    }
}

class BitcoinSetting extends Setting {
    BitcoinSetting(){
        fragment = new BitcoinSettingsFragment();
        name = "bitcoin";
    }
}

public class Settings {
    List<Setting> settings;

    public Settings(){
        settings = new ArrayList<>();
        settings.add(new GeneralSetting());
        settings.add(new PaymentSetting());
    }

    public List<Setting> getSettings() {
        return settings;
    }

    int size(){
        return settings.size();
    }

    Setting get(int i){
        return settings.get(i);
    }
}