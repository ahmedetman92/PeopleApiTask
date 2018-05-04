package com.example.ahmedetman.peopleapitask.models;

import java.util.List;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public class CharactersList {

    private List<CharacterItem> results;

    private String count;

    private String next;

    public List<CharacterItem> getResults() {
        return results;
    }

    public void setResults(List<CharacterItem> results) {
        this.results = results;
    }


    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
