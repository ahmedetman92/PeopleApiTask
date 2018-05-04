package com.example.ahmedetman.peopleapitask.presenters;

import com.example.ahmedetman.peopleapitask.models.CharacterItem;
import com.example.ahmedetman.peopleapitask.views.MainActivityView;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */
public class MainActivityPresenterImpTest {

    MainActivityPresenterImp mainActivityPresenterImp ;

    @Mock
    MainActivityView mainActivityView;


    @Before
    public void setUp() throws Exception {
        mainActivityPresenterImp = new MainActivityPresenterImp(mainActivityView);
    }

    @Test
    public void sortCharacter() throws Exception {

        List<CharacterItem> itemsAfterSort = mainActivityPresenterImp.sortCharacter(prepareListToSort());
        Object[] actual = itemsAfterSort.toArray();
        Object[] expected = prepareExpectedSortedList().toArray();
        Assert.assertArrayEquals(actual,expected);
    }

    private List<CharacterItem> prepareExpectedSortedList() {
        List<CharacterItem> characterItems = new ArrayList<>();

        CharacterItem characterItem = new CharacterItem();
        characterItem.setName("Adele");
        characterItem.setBirth_year("2002");

        CharacterItem characterItem1 = new CharacterItem();
        characterItem1.setName("Barker");
        characterItem1.setBirth_year("2008");

        CharacterItem characterItem2 = new CharacterItem();
        characterItem2.setName("Olega");
        characterItem2.setBirth_year("20013");

        CharacterItem characterItem3 = new CharacterItem();
        characterItem3.setName("Sewies");
        characterItem3.setBirth_year("2010");

        characterItems.add(characterItem);
        characterItems.add(characterItem1);
        characterItems.add(characterItem2);
        characterItems.add(characterItem3);

        return characterItems;
    }

    private List<CharacterItem> prepareListToSort() {
        List<CharacterItem> characterItems = new ArrayList<>();

        CharacterItem characterItem = new CharacterItem();
        characterItem.setName("Sewies");
        characterItem.setBirth_year("2010");

        CharacterItem characterItem1 = new CharacterItem();
        characterItem1.setName("Barker");
        characterItem1.setBirth_year("2008");

        CharacterItem characterItem2 = new CharacterItem();
        characterItem2.setName("Olega");
        characterItem2.setBirth_year("20013");

        CharacterItem characterItem3 = new CharacterItem();
        characterItem3.setName("Adele");
        characterItem3.setBirth_year("2002");

        characterItems.add(characterItem);
        characterItems.add(characterItem1);
        characterItems.add(characterItem2);
        characterItems.add(characterItem3);

        return characterItems;

    }

}