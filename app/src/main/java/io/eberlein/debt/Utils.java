package io.eberlein.debt;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class Utils {
    public static void replaceFragment(Fragment c, Fragment n){
        FragmentManager f = c.getFragmentManager();
        FragmentTransaction t = f.beginTransaction();
        t.replace(R.id.nav_host_fragment, n);
        t.addToBackStack(n.getTag());
        t.commit();
    }
}
