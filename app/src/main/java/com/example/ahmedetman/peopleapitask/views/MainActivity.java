package com.example.ahmedetman.peopleapitask.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ahmedetman.peopleapitask.ApplicationContextProvider;
import com.example.ahmedetman.peopleapitask.R;
import com.example.ahmedetman.peopleapitask.models.CharacterItem;
import com.example.ahmedetman.peopleapitask.models.caching.DBHelper;
import com.example.ahmedetman.peopleapitask.presenters.MainActivityPresenterImp;
import com.example.ahmedetman.peopleapitask.views.adapter.CharactersAdapter;

import java.util.List;

public class MainActivity extends BaseActivity implements MainActivityView {

    public static final String KEY_ITEM = "KEY_ITEM";
    private ProgressDialog progressDialog;
    private MainActivityPresenterImp mMainActivityPresenterImp;
    private RecyclerView recyclerView;
    private EditText etFilter;
    private CharactersAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        mMainActivityPresenterImp = new MainActivityPresenterImp(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_favorite:
                mMainActivityPresenterImp.onPerformFavoriteAction();
                return true;
            case R.id.menu_item_all:
                mMainActivityPresenterImp.onPerformShowAllItemsAction();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initViews() {
        recyclerView = findViewById(R.id.rv_characters);
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


    private void displayCharacterList(List<CharacterItem> characterItems) {
        if (characterItems == null || characterItems.size() == 0) {
            Toast.makeText(this,
                    MainActivity.this.getString(R.string.no_item_found),
                    Toast.LENGTH_SHORT).show();
        } else {
            adapter = new CharactersAdapter(characterItems);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        prepareAdapterListener();
    }

    private void prepareAdapterListener() {
        adapter.setOnFavClickListener((characterItem, position) -> {
            characterItem.setFavorite(true);
            DBHelper dbHelper = new DBHelper(ApplicationContextProvider.getContext());

            dbHelper.update(position);
            Toast.makeText(MainActivity.this,
                    MainActivity.this.getString(R.string.added_to_fav)
                    , Toast.LENGTH_SHORT).show();
        });

        adapter.setOnItemClickListener((characterItem, position) -> {

            Intent i = new Intent(MainActivity.this, DetailsActivity.class);
            i.putExtra(KEY_ITEM, characterItem);
            startActivity(i);
        });
    }


    @Override
    public void showCharactersList(List<CharacterItem> characterItems) {
        displayCharacterList(characterItems);
    }
}
