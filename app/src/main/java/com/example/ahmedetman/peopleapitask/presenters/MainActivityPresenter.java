package com.example.ahmedetman.peopleapitask.presenters;

import com.example.ahmedetman.peopleapitask.models.CharacterItem;

import java.util.List;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public interface MainActivityPresenter {

    /**
     * loading chanters list from the data provider
     *
     * @param charactersLists
     */
    void onLoadCharactersSuccess(List<CharacterItem> charactersLists);

    /**
     * getting failure message if the API fails
     *
     * @param error
     */
    void onLoadCharactersFail(String error);


    /**
     * caching the data that we go from api
     *
     * @param charactersLists
     */
    void cacheCharactersList(List<CharacterItem> charactersLists);


    /**
     * getting favorite items from db to show it
     */
    void onPerformFavoriteAction();


}
