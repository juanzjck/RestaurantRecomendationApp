package com.example.restaurantapp;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MyFavoriteLocations extends SugarRecord implements Serializable {
    private Locations myFavoriteLocation;
    // Default constructor is important!
    public MyFavoriteLocations() {

    }
    public MyFavoriteLocations(Locations myFavoriteLocations) {
        this.myFavoriteLocation = myFavoriteLocations;
    }

    public Locations getMyFavoriteLocations() {
        return this.myFavoriteLocation;
    }

    public void setMyFavoriteLocations(Locations myFavoriteLocation) {
        this.myFavoriteLocation = myFavoriteLocation;
    }
}
