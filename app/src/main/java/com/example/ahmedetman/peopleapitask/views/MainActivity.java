package com.example.ahmedetman.peopleapitask.views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ahmedetman.peopleapitask.ApplicationContextProvider;
import com.example.ahmedetman.peopleapitask.R;
import com.example.ahmedetman.peopleapitask.models.CharacterItem;
import com.example.ahmedetman.peopleapitask.models.caching.DBHelper;
import com.example.ahmedetman.peopleapitask.presenters.MainActivityPresenterImp;
import com.example.ahmedetman.peopleapitask.views.adapter.CharactersAdapter;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends Activity implements MainActivityView {

    private MainActivityPresenterImp mMainActivityPresenterImp;
    private RecyclerView recyclerView;
    private Button btnFavorite, btnShowAll;
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
        btnShowAll = findViewById(R.id.btn_show_all);
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

        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        prepareAdapterListener();
    }

    private void prepareAdapterListener(){
        adapter.setOnFavClickListener(new CharactersAdapter.CharacterClickListener() {
            @Override
            public void onCharacterItemClick(CharacterItem characterItem) {
//                Toast.makeText(MainActivity.this, "fav " + characterItem.getName(),
//                        Toast.LENGTH_SHORT).show();

                characterItem.setFavorite(true);
                DBHelper dbHelper = new DBHelper(ApplicationContextProvider.getContext());
                try {
                    dbHelper.createOrUpdate(characterItem);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        adapter.setOnItemClickListener(new CharactersAdapter.CharacterClickListener() {
            @Override
            public void onCharacterItemClick(CharacterItem characterItem) {
                Toast.makeText(MainActivity.this, "item " + characterItem.getName(),
                        Toast.LENGTH_SHORT).show();

//                Intent i = new Intent(MainActivity.this, DetailsActivity.class);
//
//                    Bundle b = new Bundle();
//                    b.putParcelable(APP_INFO_DATA, applicationInfo);
//                    i.putExtras(b);
//
//                startActivity(i);
            }
        });
    }
}
