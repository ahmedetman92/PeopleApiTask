package com.example.ahmedetman.peopleapitask.views;

import com.example.ahmedetman.peopleapitask.models.CharacterItem;

import java.util.List;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public interface MainActivityView {

    void showCharactersList(List<CharacterItem> characterItems);
    void showLoading();
    void hideLoading();
}
