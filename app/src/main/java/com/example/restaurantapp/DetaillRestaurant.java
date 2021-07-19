package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.List;

public class DetaillRestaurant extends AppCompatActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {
    TextView title;
    TextView description;
    TextView budget;
    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationClient;


    private String titleText;
    private String address;
    private String descriptionText;
    private Double budgetText;
    private Double lt;
    private Double ln;
    private String image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaill_restaurant);
        title=findViewById(R.id.title);
        title.setText((String) getIntent().getExtras().getSerializable("title").toString());
        description=findViewById(R.id.description);
        description.setText((String) getIntent().getExtras().getSerializable("description").toString());
        budget=findViewById(R.id.budget);
        budget.setText("$"+(String) getIntent().getExtras().getSerializable("budget").toString());


        titleText=getIntent().getExtras().getSerializable("title").toString();
        address=getIntent().getExtras().getSerializable("address").toString();
        descriptionText=getIntent().getExtras().getSerializable("description").toString();
        budgetText=new Double(getIntent().getExtras().getSerializable("budget").toString());
        lt=new Double(getIntent().getExtras().getSerializable("lt").toString());
        ln=new Double(getIntent().getExtras().getSerializable("lg").toString());
        image="";

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


       // ;

        //specify the location of media file


    }

    public void saveAsMyFavorite(View v){
        //verficar si hay una lista craeda

        MyFavoriteLocations myFavoriteLocation=new MyFavoriteLocations();
        myFavoriteLocation.setMyFavoriteLocations(new Locations(1,titleText, address, descriptionText, budgetText, lt, ln, "df"));

        myFavoriteLocation.save();
    }


    //get my loation
    public void getMyLocation(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            LatLng marker = new LatLng(location.getLatitude(), location.getLongitude());
                            mMap.addMarker(new MarkerOptions().position(marker).title("You").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 15.0f));
                        }
                    }
                });

    }
    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;


        LatLng marker = new LatLng(new Double(getIntent().getExtras().getSerializable("lt").toString()), new Double(getIntent().getExtras().getSerializable("lg").toString()));

        mMap.addMarker(new MarkerOptions().position(marker).title((String) getIntent().getExtras().getSerializable("title").toString()).snippet(""+getIntent().getExtras().getSerializable("address").toString()));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));

        mMap.setOnMarkerClickListener(this);
        getMyLocation();
    }


}