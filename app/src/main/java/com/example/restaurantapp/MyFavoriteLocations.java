package com.example.restaurantapp;

import java.util.ArrayList;

public class MyFavoriteLocations {
    private ArrayList<Locations> myFavoriteLocations;

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
