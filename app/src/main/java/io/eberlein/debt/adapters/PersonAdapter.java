package io.eberlein.debt.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import io.eberlein.debt.Person;
import io.eberlein.debt.Persons;
import io.eberlein.debt.R;
import io.eberlein.debt.Static;
import io.eberlein.debt.Utils;
import io.eberlein.debt.events.PersonDeletedEvent;
import io.eberlein.debt.ui.PersonFragment;
import io.paperdb.Paper;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private Context ctx;
    private Persons persons;
    private Fragment currentFragment;
    private String currencyString;

    public PersonAdapter(Context ctx, Fragment currentFragment, Persons persons){
        this.ctx = ctx;
        this.currentFragment = currentFragment;
        this.persons = persons;
        currencyString = Paper.book(Static.BOOK_SETTINGS).read("currency", "€");
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, int position) {
        holder.setPerson(persons.get(position));
        if ((position % 2) != 0) holder.layout.setBackgroundColor(Color.parseColor("#424242"));
        else holder.layout.setBackgroundColor(Color.parseColor("#666666"));
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private Person person;
        private boolean extraMenuOpen = false;

        @BindView(R.id.layout)
        RelativeLayout layout;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.debt) TextView debt;
        @BindView(R.id.currency) TextView currency;
        @BindView(R.id.delete) Button delete;

        @OnClick(R.id.delete)
        void deleteClicked(){
            closeExtraMenu();
            EventBus.getDefault().post(new PersonDeletedEvent(person));
        }

        @OnClick
        void onClick(){
            if(!extraMenuOpen) Utils.replaceFragment(currentFragment, new PersonFragment(person));
            else closeExtraMenu();
        }

        @OnLongClick
        void onLongClick(){
            if(!extraMenuOpen) openExtraMenu();
            else closeExtraMenu();
        }

        void openExtraMenu(){
            extraMenuOpen = true;
            currency.setVisibility(View.GONE);
            debt.setVisibility(View.GONE);
            delete.setVisibility(View.VISIBLE);
        }

        void closeExtraMenu(){
            extraMenuOpen = false;
            currency.setVisibility(View.VISIBLE);
            debt.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
        }

        ViewHolder(View v){
            super(v);
            ButterKnife.bind(this, v);
        }

        void setPerson(Person person){
            this.person = person;
            name.setText(this.person.getName());
            double d = this.person.getDebt();
            debt.setText(String.valueOf(d));
            if(d < 0) debt.setTextColor(Color.RED);
            currency.setText(currencyString);
        }
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(ctx).inflate(R.layout.item_persons, parent, false));
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }
}
