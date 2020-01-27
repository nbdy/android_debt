package io.eberlein.debt.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.debt.R;
import io.eberlein.debt.Utils;
import io.eberlein.debt.settings.Setting;
import io.eberlein.debt.settings.Settings;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private Context ctx;
    private Fragment currentFragment;
    private Settings settings;

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setSetting(settings.get(position));
    }

    public SettingsAdapter(Context ctx, Fragment currentFragment, Settings settings) {
        this.ctx = ctx;
        this.currentFragment = currentFragment;
        this.settings = settings;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Setting setting;

        @BindView(R.id.layout)
        RelativeLayout layout;
        @BindView(R.id.name) TextView name;

        @OnClick
        void onClick(){
            Utils.replaceFragment(currentFragment, setting.getFragment());
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setSetting(Setting s){
            setting = s;
            name.setText(setting.getName());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_setting, parent, false));
    }

    @Override
    public int getItemCount() {
        return settings.size();
    }
}
