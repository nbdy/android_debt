package io.eberlein.debt.settings;

import io.eberlein.debt.ui.BitcoinSettingsFragment;

public class BitcoinSetting extends Setting {
    public BitcoinSetting(){
        fragment = new BitcoinSettingsFragment();
        name = "bitcoin";
    }
}
