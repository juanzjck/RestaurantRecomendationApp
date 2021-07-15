package com.example.restaurantapp;



import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.List;

public class Contacto extends SugarRecord implements Serializable {
    private String image;
    private String name;
    private String phone;
    private String address;
    String lat;
    String lng;

    public Contacto(String image, String name, String phone, String address, String lat, String lng) {
        this.image = image;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }

    public Contacto(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) { this.lat = lat; }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }


}
