package com.example.ahmedetman.peopleapitask.models;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by Ahmed Etman on 5/4/2018.
 */

@DatabaseTable(tableName = "characters")
public class CharacterItem {

    @DatabaseField(id = true, useGetSet = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @DatabaseField(columnName = "edited")
    private String edited;

    @DatabaseField(columnName = "skin_color")
    private String skin_color;

    @DatabaseField(columnName = "eye_color")
    private String eye_color;

    @DatabaseField(columnName = "birth_year")
    private String birth_year;

    @DatabaseField(columnName = "url")
    private String url;

    @DatabaseField(columnName = "mass")
    private String mass;

    @DatabaseField(columnName = "height")
    private String height;

    @DatabaseField(columnName = "created")
    private String created;

    @DatabaseField(columnName = "hair_color")
    private String hair_color;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "gender")
    private String gender;

    @DatabaseField(columnName = "homeworld")
    private String homeworld;

    @DatabaseField(columnName = "isFavorite")
    private boolean isFavorite;

    @DatabaseField(columnName = "vehicles", dataType = DataType.SERIALIZABLE)
    private ArrayList<String> vehicles;

    public ArrayList<String> getVehicles() {
        return vehicles;
    }

    public void setVehicles(ArrayList<String> vehicles) {
        this.vehicles = vehicles;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String getEdited ()
    {
        return edited;
    }

    public void setEdited (String edited)
    {
        this.edited = edited;
    }

    public String getSkin_color ()
    {
        return skin_color;
    }

    public void setSkin_color (String skin_color)
    {
        this.skin_color = skin_color;
    }

    public String getEye_color ()
    {
        return eye_color;
    }

    public void setEye_color (String eye_color)
    {
        this.eye_color = eye_color;
    }

    public String getBirth_year ()
    {
        return birth_year;
    }

    public void setBirth_year (String birth_year)
    {
        this.birth_year = birth_year;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    public String getMass ()
    {
        return mass;
    }

    public void setMass (String mass)
    {
        this.mass = mass;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getCreated ()
    {
        return created;
    }

    public void setCreated (String created)
    {
        this.created = created;
    }

    public String getHair_color ()
    {
        return hair_color;
    }

    public void setHair_color (String hair_color)
    {
        this.hair_color = hair_color;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getHomeworld ()
    {
        return homeworld;
    }

    public void setHomeworld (String homeworld)
    {
        this.homeworld = homeworld;
    }


    @Override
    public boolean equals(Object obj) {
        boolean result = false;

        if (obj != null && obj instanceof CharacterItem) {
            CharacterItem newItem = (CharacterItem) obj;
            if (newItem.getName().equals(this.getName()) &&
                    newItem.getBirth_year().equals(this.getBirth_year()))

                result = true;
        }
        return result;
    }


    /*
    newItem.getCreated().equals(this.getCreated()) &&
                    newItem.getEdited().equals(this.getEdited()) &&
                    newItem.getEye_color().equals(this.getEye_color()) &&
                    Arrays.equals(newItem.getFilms(), this.getFilms()) &&
                    newItem.getGender().equals(this.getGender()) &&
                    newItem.getHair_color().equals(this.getHair_color()) &&
                    newItem.getHeight().equals(this.getHeight()) &&
                    newItem.getHomeworld().equals(this.getHomeworld()) &&
                    newItem.getMass().equals(this.getMass()) &&
                    newItem.getSkin_color().equals(this.getSkin_color()) &&
                    Arrays.equals(newItem.getSpecies(), this.getSpecies()) &&
                    newItem.getUrl().equals(this.getUrl()) &&
                    newItem.getMass().equals(this.getMass()) &&
                    Arrays.equals(newItem.getVehicles(), this.getVehicles())
     */
}
