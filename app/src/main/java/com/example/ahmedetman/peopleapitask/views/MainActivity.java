package com.example.ahmedetman.peopleapitask.views;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.ahmedetman.peopleapitask.R;
import com.example.ahmedetman.peopleapitask.models.CharacterItem;
import com.example.ahmedetman.peopleapitask.presenters.MainActivityPresenterImp;
import com.example.ahmedetman.peopleapitask.views.adapter.CharactersAdapter;

import java.util.List;

public class MainActivity extends Activity implements MainActivityView {

    private MainActivityPresenterImp mMainActivityPresenterImp;
    private RecyclerView recyclerView;
    private Button btnFavorite;
    private EditText etFilter;
    private CharactersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mMainActivityPresenterImp = new MainActivityPresenterImp(this);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_characters);
        btnFavorite = findViewById(R.id.btn_show_favourite);
        etFilter = findViewById(R.id.et_filter);

        etFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void showCharactersList(List<CharacterItem> characterItems) {
        displayCharacterList(characterItems);
    }


    private void displayCharacterList(List<CharacterItem> characterItems){
        adapter = new CharactersAdapter(characterItems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
