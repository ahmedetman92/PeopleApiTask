package com.example.ahmedetman.peopleapitask.views;

import com.example.ahmedetman.peopleapitask.models.CharacterItem;

import java.util.List;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public interface MainActivityView extends BaseActivityActions{

    void showCharactersList(List<CharacterItem> characterItems);
}
