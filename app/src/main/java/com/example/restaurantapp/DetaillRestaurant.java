package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.List;

public class DetaillRestaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill_restaurant);

        //specify the location of media file


    }

    public void saveAsMyFavorite(View v){
        List<MyFavoriteLocations> mylocations = MyFavoriteLocations.listAll(MyFavoriteLocations.class);
        if(mylocations.size()>0){
            MyFavoriteLocations mylocationsSaved= mylocations.get(0);
            mylocationsSaved.getMyFavoriteLocations().add(null);
        }else{

        }
    }
}