package io.eberlein.debt.settings;

import java.util.ArrayList;
import java.util.List;


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

    public int size(){
        return settings.size();
    }

    public Setting get(int i){
        return settings.get(i);
    }
}