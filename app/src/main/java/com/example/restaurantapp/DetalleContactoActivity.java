package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DetalleContactoActivity extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);
        // recoger los parametros
        Intent i= getIntent();
        String name = i.getExtras().getString("Nombre");
        String Telefono  = i.getExtras().getString("Telefono");
        String Direccion = i.getExtras().getString("Direccion");
        String image = i.getExtras().getString("Img");
        // TextView textView =()
        TextView txtNombre = (TextView)findViewById(R.id.txtNombre) ;
        TextView txtPhone = (TextView) findViewById(R.id.txtPhone);
        TextView txtDirecion = (TextView) findViewById(R.id.txtAddress);
        ImageView imgContacto =(ImageView) findViewById(R.id.imgContact);

        txtNombre.setText(name);
        txtPhone.setText(Telefono);
        txtDirecion.setText(Direccion);
        imgContacto.setImageBitmap(BitmapFactory.decodeFile(image));

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-1.831239, -78.183406);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}