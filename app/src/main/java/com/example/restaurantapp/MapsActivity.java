package com.example.restaurantapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Locations> locations = new ArrayList<Locations>();
    private ArrayList<Locations> filteredLocations = new ArrayList<Locations>();
    private FusedLocationProviderClient fusedLocationClient;
    private EditText maxInput,minInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        maxInput= findViewById(R.id.Max);
        minInput= findViewById(R.id.Min);
        minInput.setText("0");
        maxInput.setText("100");
        //config location default


        locations.add(new Locations("Cafe san blas", "Jose de antepara E4-09", "Cafeteria tradicional de comida italiana, pizza, pastas y postres. \n Precio para todo los bolsillos", 20.0, -0.2186399, -78.5072506, "df"));
        locations.add(new Locations("Museo de Arte Colonial", "Jose de antepara E4-09", "Cafeteria tradicional de comida italiana, pizza, pastas y postres. \n Precio para todo los bolsillos", 10.0, -0.2176353, -78.5131746, "df"));
        locations.add(new Locations("Panorama", "Jose de antepara E4-09", "Cafeteria tradicional de comida italiana, pizza, pastas y postres. \n Precio para todo los bolsillos", 5.0, -0.2146713, -78.5103162, "df"));
        locations.add(new Locations("Centro de Arte Contemporáneo de Quito", "Jose de antepara E4-09", "Cafeteria tradicional de comida italiana, pizza, pastas y postres. \n Precio para todo los bolsillos", 20.0, -0.2113522, -78.5070416, "df"));


        filteredLocations=locations;



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */



    //interactuable marker
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        getMyLocation();
        loadMakers();


    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        if(marker.getTitle()!="You"){
            Intent intent = new Intent(getApplicationContext(),DetaillRestaurant.class);

            startActivity(intent);

        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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
    //load markers
    public void loadMakers(){
        for (Locations location : filteredLocations) {
            // Add a marker in Sydney and move the camera
            LatLng marker = new LatLng(location.getLt(), location.getLn());

            mMap.addMarker(new MarkerOptions().position(marker).title(location.getTitle()).snippet(location.getDescription()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(marker));

            mMap.setOnMarkerClickListener(this);
        }
    }
    public void Filter(View v){

        if(maxInput.getText().toString()!="" && minInput.getText().toString()!=""){
            double max= new Double(maxInput.getText().toString());
            double min= new Double(minInput.getText().toString());
            filteredLocations=new ArrayList<Locations>();
            for (Locations location:locations) {
                if(location.getBudget()<max && location.getBudget()>min ){
                    filteredLocations.add(location);
                }
            }
            mMap.clear();
        }

        getMyLocation();
        loadMakers();

    }
}