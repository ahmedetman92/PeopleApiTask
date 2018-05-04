package com.example.ahmedetman.peopleapitask.network;

import com.example.ahmedetman.peopleapitask.models.CharactersList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

public interface ApiService {

    @GET("api/people")
    Call<CharactersList> getCharactersCall();
}
