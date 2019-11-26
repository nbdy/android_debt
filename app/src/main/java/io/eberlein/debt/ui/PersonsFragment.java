package io.eberlein.debt.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.eberlein.debt.Person;
import io.eberlein.debt.PersonAdapter;
import io.eberlein.debt.Persons;
import io.eberlein.debt.R;
import io.eberlein.debt.Utils;
import io.eberlein.debt.events.PersonDeletedEvent;

public class PersonsFragment extends Fragment {
    @BindView(R.id.recycler) RecyclerView recycler;

    private Persons persons;
    private PersonAdapter adapter;

    @OnClick(R.id.add)
    void add(){
        Utils.replaceFragment(this, new PersonFragment(new Person()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PersonDeletedEvent e){
        persons.archive(e.getPerson());
        adapter.notifyDataSetChanged();
    }

    public PersonsFragment(){
        persons = Persons.get();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_persons, container, false);
        ButterKnife.bind(this, v);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new PersonAdapter(getContext(), this, persons);
        recycler.setAdapter(adapter);
        return v;
    }
}
