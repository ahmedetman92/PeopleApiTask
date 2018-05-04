package com.example.ahmedetman.peopleapitask.presenters;

import com.example.ahmedetman.peopleapitask.ApplicationContextProvider;
import com.example.ahmedetman.peopleapitask.models.CharacterItem;
import com.example.ahmedetman.peopleapitask.models.CharactersListDataProvider;
import com.example.ahmedetman.peopleapitask.models.caching.DBHelper;
import com.example.ahmedetman.peopleapitask.views.MainActivityView;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public class MainActivityPresenterImp implements MainActivityPresenter {


    private MainActivityView mMainActivityView;
    public MainActivityPresenterImp(MainActivityView mainActivityView) {
        this.mMainActivityView = mainActivityView;
        CharactersListDataProvider.getInstance().getAllCharacters(this);
    }

    @Override
    public void onLoadCharactersSuccess(List<CharacterItem> charactersLists) {
        mMainActivityView.showCharactersList(sortCharacter(charactersLists));
    }

    @Override
    public void onLoadCharactersFail(String error) {

    }

    @Override
    public void cacheCharactersList(List<CharacterItem> charactersLists) {
        DBHelper dbHelper = new DBHelper(ApplicationContextProvider.getContext());
        int index = 0;
        for (CharacterItem characterItem :
                charactersLists) {
            try {
                characterItem.setId(index);
                dbHelper.createOrUpdate(characterItem);
                index++;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * sorting the list alphabetically
     * @param list
     * @return
     */
    public List<CharacterItem> sortCharacter(List<CharacterItem> list) {
        if (list.size() > 0) {
            Collections.sort(list, new Comparator<CharacterItem>() {
                @Override
                public int compare(final CharacterItem item1, final CharacterItem item2) {
                    return item1.getName().compareTo(item2.getName());
                }
            });
            return list;
        }else
            return null;
    }

    private void getFavorite(){
        List<CharacterItem> listToFilter = CharactersListDataProvider.getInstance().getOfflineItems();
        List<CharacterItem> favoriteList = listToFilter.stream()
                .filter(CharacterItem::isFavorite).collect(Collectors.toList());
    }
}
