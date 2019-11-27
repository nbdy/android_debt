package io.eberlein.debt.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.debt.Person;
import io.eberlein.debt.R;
import io.eberlein.debt.Static;
import io.eberlein.debt.adapters.ProfitAdapter;
import io.eberlein.debt.events.ProfitDeletedEvent;
import io.paperdb.Paper;

public class PersonFragment extends Fragment {
    private Person person;
    private ProfitAdapter adapter;

    @BindView(R.id.recycler) RecyclerView recycler;
    @BindView(R.id.name) EditText name;
    @BindView(R.id.from) EditText from;

    @OnClick(R.id.add)
    void add() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater i = requireActivity().getLayoutInflater();
        View v = i.inflate(R.layout.dialog_profit, null, false);
        ((TextView) v.findViewById(R.id.currency)).setText(Paper.book(Static.BOOK_SETTINGS).read("currency", "€"));
        builder.setView(v)
                .setPositiveButton("add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        double a = Double.valueOf(((EditText) v.findViewById(R.id.amount)).getText().toString());
                        String w = ((EditText) v.findViewById(R.id.why)).getText().toString();
                        Log.d("PersonFragment.add.Dialog", String.valueOf(a));
                        Log.d("PersonFragment.add.Dialog", w);
                        person.addProfit(a, w);
                        person.save();
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onProfitDeleted(ProfitDeletedEvent e){
        person.removeProfit(e.getProfit());
        person.save();
        adapter.notifyDataSetChanged();
    }

    public PersonFragment(Person person){
        this.person = person;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, v);
        adapter = new ProfitAdapter(getContext(), this.person.getProfits());
        name.setText(this.person.getName());
        from.setText(this.person.getFrom());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(adapter);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onDestroy() {
        person.setName(name.getText().toString());
        person.setFrom(from.getText().toString());
        person.save();
        super.onDestroy();
    }
}
