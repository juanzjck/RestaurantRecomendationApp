package com.example.restaurantapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.example.restaurantapp.MainActivity.SHARED_PREFS;

public class NuevoContacto extends AppCompatActivity {

    String image ="";
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    int count=0;
    EditText editTextNCName, editTextNCPhone, editTextNCAddress;
    ImageView imageViewNCImage;
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_contacto);
        imageViewNCImage = findViewById(R.id.imageViewNCImage);
        editTextNCName = findViewById(R.id.editTextNCName);
        editTextNCPhone = findViewById(R.id.editTextNCPhone);
        editTextNCAddress = findViewById(R.id.editTextNCAddress);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPhone = (TextView) findViewById(R.id.tvPhone);
        tvAddress  = (TextView) findViewById(R.id.tvAddress);
        //updateViews();

        prefs  = getSharedPreferences("contacto", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void onClickAceptar(View v){
        String name = editTextNCName.getText().toString();
        String phone = editTextNCPhone.getText().toString();
        String address = editTextNCAddress.getText().toString();

        double[] LatLng = getLocationFromAddress(address);

        String lat = String.valueOf(LatLng[0]);
        String lng = String.valueOf(LatLng[1]);

        System.out.println("Lat:"+lat+" Lng: "+lng);

        Contacto contacto = new Contacto(image,name,phone,address,lat,lng);
        contacto.save();

        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public double[] getLocationFromAddress(String strAddress){
        Geocoder coder = new Geocoder(this);
        double [] LatLng = new double[2];
        List<Address> address;
        //Barcode.GeoPoint p1 = null;
        try {
            address = coder.getFromLocationName(strAddress,5);
            if (address==null) {
                return null;
            }
            Address location=address.get(0);
            LatLng[0] = location.getLatitude();
            LatLng[1] = location.getLongitude();
        }catch (IOException e){}

        return LatLng;
    }


    public  void  onClickCancelar(View v){
        Intent vista = new Intent(this,MainActivity.class);
        startActivity(vista);
    }

    public void onClickTakePic(View v){
        switch (v.getId()){
            case R.id.buttonCamara:
                count = prefs.getInt("imagen",0);
                count++;
                image = getExternalFilesDir(Environment.DIRECTORY_PICTURES)+ "/" + "fotouser"+count+".jpg";
                System.out.println(image);

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                editor.putInt("imagen",count);
                editor.commit();

                //Uri output = Uri.fromFile(new File(photo));
                Uri output = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID+".fileprovider"
                        , new File(image));


                intent.putExtra(MediaStore.EXTRA_OUTPUT, output);
                startActivityForResult(intent, 0);
                break;
            case R.id.buttonGaleria:
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto, 1);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 0:
                if (resultCode == this.RESULT_OK) {
                    File fileTemp = new File(image);
                    if (!fileTemp.exists()) {
                        Toast.makeText(this,
                                "No se ha realizado la foto", Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        imageViewNCImage.setImageBitmap(BitmapFactory.decodeFile(image));
                    }
                }

                break;
            case 1:
                if (resultCode == this.RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};
                    Cursor cursor = this.getContentResolver().query(uri, projection,
                            null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    image = cursor.getString(columnIndex); // returns null
                    cursor.close();
                    imageViewNCImage.setImageBitmap(BitmapFactory.decodeFile(image));
                }
                break;
            case 2:
                if(resultCode == Activity.RESULT_OK){
                    if(data != null){
                        Contacto contacto = (Contacto) data.getSerializableExtra("contacto");
                        editTextNCName.setText(contacto.getName());
                        editTextNCPhone.setText(contacto.getPhone());
                        editTextNCAddress.setText(contacto.getAddress());
                    }
                }
                break;
        }

    }

    public void updateViews(){
        SharedPreferences SP =  getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);;
        SharedPreferences.Editor editor = SP.edit();
        String name = SP.getString(getString(R.string.NAME),"");
        String phone = SP.getString(getString(R.string.PHONE),"");
        String address = SP.getString(getString(R.string.ADDRESS),"");
        tvName.setText(name);
        tvPhone.setText(phone);
        tvAddress.setText(address);
    }
    
}