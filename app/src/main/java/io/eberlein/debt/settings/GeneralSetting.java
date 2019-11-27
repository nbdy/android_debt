package io.eberlein.debt.settings;

import io.eberlein.debt.ui.GeneralSettingsFragment;

public class GeneralSetting extends Setting {
    public GeneralSetting(){
        fragment = new GeneralSettingsFragment();
        name = "general";
    }
}
