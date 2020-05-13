package io.eberlein.debt.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.eberlein.debt.R;
import io.eberlein.debt.Static;
import io.eberlein.debt.events.DebtDeletedEvent;
import io.eberlein.debt.events.DebtPayedEvent;
import io.eberlein.debt.objects.Debt;
import io.eberlein.debt.objects.Debts;
import io.paperdb.Paper;

public class DebtAdapter extends RecyclerView.Adapter<DebtAdapter.ViewHolder> {
    private Context ctx;
    private Debts debts;
    private String currencyString;

    public DebtAdapter(Context ctx, Debts debts) {
        this.ctx = ctx;
        this.debts = debts;
        currencyString = Paper.book(Static.BOOK_SETTINGS).read("currency", "€");
    }

    @NonNull
    @Override
    public DebtAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_profit, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setDebt(debts.get(position));
    }

    @Override
    public int getItemCount() {
        return debts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private boolean extraMenuOpen = false;
        @BindView(R.id.payed)
        TextView payed;

        @BindView(R.id.layout)
        RelativeLayout layout;

        @BindView(R.id.amount) TextView amount;
        @BindView(R.id.why) TextView why;
        private Debt debt;
        @BindView(R.id.timestamp) TextView timestamp;
        @BindView(R.id.currency) TextView currency;
        @BindView(R.id.delete) Button delete;

        @OnClick
        void onClick(){
            if (!extraMenuOpen && !debt.isPayed()) {
                AlertDialog.Builder b = new AlertDialog.Builder(ctx, R.style.CustomDialog);
                View v = LayoutInflater.from(ctx).inflate(R.layout.dialog_pay, null, false);
                b.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (((RadioButton) v.findViewById(R.id.cash)).isChecked()) {
                            EventBus.getDefault().post(new DebtPayedEvent(debt));
                            // todo maybe ask user if he wants to take a pic of the bounty
                        } else if (((RadioButton) v.findViewById(R.id.paypal)).isChecked()) {
                            Toast.makeText(ctx, "not implemeted", Toast.LENGTH_SHORT).show();
                        } else if (((RadioButton) v.findViewById(R.id.bitcoin)).isChecked()) {
                            Toast.makeText(ctx, "not implemented", Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
                    }
                });
                b.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                b.setView(v).show();
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
            EventBus.getDefault().post(new DebtDeletedEvent(debt));
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

        void setDebt(Debt p) {
            debt = p;
            double a = p.getAmount();
            amount.setText(String.valueOf(a));
            if(a < 0) amount.setTextColor(Color.RED);
            why.setText(p.getWhy());
            timestamp.setText(p.getTimestamp().toString());
            currency.setText(currencyString);
            payed.setText(p.isPayed() ? "payed" : "unpayed");
            if (!p.isPayed()) payed.setTextColor(Color.RED);
        }
    }

}
