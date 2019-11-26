package io.eberlein.debt.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;
import io.eberlein.debt.R;
import io.eberlein.debt.Static;
import io.paperdb.Book;
import io.paperdb.Paper;

public class GeneralSettingsFragment extends Fragment {
    @BindView(R.id.currency) Spinner currency;

    private Book book;

    @OnItemSelected(R.id.currency)
    void onCurrencySelected(Spinner spinner, int position){
        book.write("currency", spinner.getItemAtPosition(position));
        book.write("currencyIndex", position);
    }

    public GeneralSettingsFragment(){
        book = Paper.book(Static.BOOK_SETTINGS);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings_general, container,false);
        ButterKnife.bind(this, v);
        currency.setAdapter(
                ArrayAdapter.createFromResource(
                        getContext(),
                        R.array.currencies,
                        android.R.layout.simple_spinner_item));
        currency.setSelection(book.read("currencyIndex", 0));
        return v;
    }
}
