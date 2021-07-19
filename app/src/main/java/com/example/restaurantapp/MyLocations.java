package com.example.restaurantapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.List;

public class MyLocations extends AppCompatActivity {
    RecyclerView recyclerViewLocations;
    MyLocationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_locations);

        recyclerViewLocations = findViewById(R.id.recyclerViewLocations);
        recyclerViewLocations.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLocations.setLayoutManager(layoutManager);

        List<MyFavoriteLocations> listLocations = MyFavoriteLocations.listAll(MyFavoriteLocations.class);

        adapter = new MyLocationAdapter(listLocations);

        recyclerViewLocations.setAdapter(adapter);
        adapter.setListener(new MyLocationAdapter.LocationListener() {
            @Override
            public void onLocationListener(int position) {
              //  Intent intent = new Intent(MainActivity.this, DetalleContactoActivity.class);
              //  intent.putExtra("Contacto", listContactos.get(position));
             //   startActivity(intent);
            }

            @Override
            public void onLocationDelete(int position) {
              /* AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Seguro que desea eliminar?")
                        .setTitle("Eliminar Contacto")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Contacto contacto = listContactos.get(position);
                                contacto.delete();
                                loadData();
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        });
                builder.show();*/
            }
        });
    }
}