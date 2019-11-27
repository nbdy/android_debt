package io.eberlein.debt.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.eberlein.debt.Profit;
import io.eberlein.debt.Profits;
import io.eberlein.debt.R;
import io.eberlein.debt.Static;
import io.eberlein.debt.events.ProfitDeletedEvent;
import io.paperdb.Paper;

public class ProfitAdapter extends RecyclerView.Adapter<ProfitAdapter.ViewHolder> {
    private Context ctx;
    private Profits profits;
    private String currencyString;

    class ViewHolder extends RecyclerView.ViewHolder {
        private Profit profit;
        private boolean extraMenuOpen = false;

        @BindView(R.id.amount) TextView amount;
        @BindView(R.id.why) TextView why;
        @BindView(R.id.timestamp) TextView timestamp;
        @BindView(R.id.currency) TextView currency;
        @BindView(R.id.delete) Button delete;

        @OnClick
        void onClick(){
            if(!extraMenuOpen) {
                //AlertDialog.Builder b = new AlertDialog.Builder(ctx);
                //LayoutInflater i = LayoutInflater.from(ctx).inflate()
                Toast.makeText(ctx, "to be implemented", Toast.LENGTH_SHORT).show();
            } else closeExtraMenu();

        }

        @OnLongClick
        void onLongClick(){
            if(!extraMenuOpen) openExtraMenu();
            else closeExtraMenu();
        }

        @OnClick(R.id.delete)
        void deleteClicked(){
            closeExtraMenu();
            EventBus.getDefault().post(new ProfitDeletedEvent(profit));
        }

        void openExtraMenu(){
            extraMenuOpen = true;
            timestamp.setVisibility(View.GONE);
            why.setVisibility(View.GONE);
            delete.setVisibility(View.VISIBLE);
        }

        void closeExtraMenu(){
            extraMenuOpen = false;
            timestamp.setVisibility(View.VISIBLE);
            why.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setProfit(Profit p){
            profit = p;
            double a = p.getAmount();
            amount.setText(String.valueOf(a));
            if(a < 0) amount.setTextColor(Color.RED);
            else amount.setTextColor(Color.BLACK);
            why.setText(p.getWhy());
            timestamp.setText(p.getTimestamp().toString());
            currency.setText(currencyString);
        }
    }

    public ProfitAdapter(Context ctx, Profits profits){
        this.ctx = ctx;
        this.profits = profits;
        currencyString = Paper.book(Static.BOOK_SETTINGS).read("currency", "€");
    }

    @NonNull
    @Override
    public ProfitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_profit, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setProfit(profits.get(position));
    }

    @Override
    public int getItemCount() {
        return profits.size();
    }

}
