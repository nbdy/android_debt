package io.eberlein.debt.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.eberlein.debt.settings.PaymentSettings;
import io.eberlein.debt.R;
import io.eberlein.debt.SettingsAdapter;

public class PaymentSettingsFragment extends Fragment {
    @BindView(R.id.recycler) RecyclerView recycler;
    @BindView(R.id.tv_header) TextView header;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, v);
        header.setText(getResources().getText(R.string.payment_settings));
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(new SettingsAdapter(getContext(), this, new PaymentSettings()));
        return v;
    }
}
