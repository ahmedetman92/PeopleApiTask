package com.example.ahmedetman.peopleapitask.models;

import com.example.ahmedetman.peopleapitask.ApplicationContextProvider;
import com.example.ahmedetman.peopleapitask.R;
import com.example.ahmedetman.peopleapitask.models.caching.DBHelper;
import com.example.ahmedetman.peopleapitask.network.ApiService;
import com.example.ahmedetman.peopleapitask.network.RetroClient;
import com.example.ahmedetman.peopleapitask.presenters.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public class CharactersListDataProvider {

    private static CharactersListDataProvider instance = null;

    public static CharactersListDataProvider getInstance() {
        if (instance == null) {
            instance = new CharactersListDataProvider();
        }
        return instance;
    }


    /**
     * getting all characters
     *
     * @return
     */
    public void getAllCharacters(final MainActivityPresenter mainActivityPresenter) {

        DBHelper dbHelper = new DBHelper(ApplicationContextProvider.getContext());

        List<CharacterItem> characterItems = dbHelper.getAll(CharacterItem.class);

        //Creating an object of our api interface
        ApiService api = RetroClient.getApiService();

        /**
         * Calling JSON
         */
        Call<CharactersList> call = api.getCharactersCall();

        /**
         * Enqueue Callback will be call when get response...
         */
        call.enqueue(new Callback<CharactersList>() {
            @Override
            public void onResponse(Call<CharactersList> call, Response<CharactersList> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().getResults() != null) {
                        mainActivityPresenter.onLoadCharactersSuccess(response.body().getResults());
                        mainActivityPresenter.cacheCharactersList(response.body().getResults());
                    }else {
                        mainActivityPresenter.onLoadCharactersFail(ApplicationContextProvider
                                .getContext().getString(R.string.error_empty_body));
                    }
                }
            }

            @Override
            public void onFailure(Call<CharactersList> call, Throwable t) {
                mainActivityPresenter.onLoadCharactersFail(t.getMessage());
            }
        });
    }
}
