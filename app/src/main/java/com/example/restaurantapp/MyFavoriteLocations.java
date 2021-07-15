package com.example.restaurantapp;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;

public class MyFavoriteLocations extends SugarRecord implements Serializable {
    private ArrayList<Locations> myFavoriteLocations;
    // Default constructor is important!
    public MyFavoriteLocations() {

    }
    public MyFavoriteLocations(ArrayList<Locations> myFavoriteLocations) {
        this.myFavoriteLocations = myFavoriteLocations;
    }

    public ArrayList<Locations> getMyFavoriteLocations() {
        return myFavoriteLocations;
    }

    public void setMyFavoriteLocations(ArrayList<Locations> myFavoriteLocations) {
        this.myFavoriteLocations = myFavoriteLocations;
    }
}
