package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.example.restaurantapp.Contacto.*;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewContactos;
    ContactosAdapter contactosAdapter;

    public static final String SHARED_PREFS = "sharedPrefs";

    private TextView textViewName;
    private TextView textViewDirection;
    private TextView textViewPhone;
    private TextView textViewMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewContactos = findViewById(R.id.recyclerViewContactos);

        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewDirection = (TextView) findViewById(R.id.textViewDirection);
        textViewPhone = (TextView) findViewById(R.id.textViewPhone);
        textViewMail = (TextView) findViewById(R.id.textViewMail);

        List<Contacto> listaContacto = listAll(Contacto.class);
        //List<Contacto> listaContactos = new ArrayList<Contacto>();


        recyclerViewContactos.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewContactos.setLayoutManager((layoutManager));

        ContactosAdapter adapter = new ContactosAdapter(listaContacto);


//Metodo para mostrar los contactos
        adapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contacto contactoView = listaContacto.get(recyclerViewContactos.getChildAdapterPosition(v));
                open(v,contactoView);// tomar los datos y abrir nueva ventada los parametros

            }
        });
        recyclerViewContactos.setAdapter(adapter);
        loadData();
        updateViews();

    }



    public  void onClickNuevoContacto(View v){
        Intent vista = new Intent(this,NuevoContacto.class);
        startActivity(vista);
        finish(); //destruir la vista

    }
    public void open(View v,Contacto contacto){
        Intent activity = new Intent(this,DetalleContactoActivity.class);
        activity.putExtra("Img",contacto.getImage());
        activity.putExtra("Nombre",contacto.getName());
        activity.putExtra("Telefono",contacto.getPhone());
        activity.putExtra("Direccion",contacto.getAddress());
        startActivity(activity);
    }

    public void loadData(){
        SharedPreferences SP = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = SP.edit();
        editor.putString(getString(R.string.NAME),"Restaurante RFC");
        editor.putString(getString(R.string.PHONE),"0984493015");
        editor.putString(getString(R.string.ADDRESS),"Av.de los Granados");
        editor.putString(getString(R.string.MAIL),"rfc@udla.edu.ec");
        editor.apply();
    }

    public void updateViews(){
        SharedPreferences SP = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        textViewName.setText(SP.getString(getString(R.string.NAME),""));
        textViewDirection.setText(SP.getString(getString(R.string.ADDRESS),""));
        textViewPhone.setText(SP.getString(getString(R.string.PHONE),""));
        textViewMail.setText(SP.getString(getString(R.string.MAIL),""));
    }
}