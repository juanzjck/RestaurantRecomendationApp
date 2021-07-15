package com.example.restaurantapp;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Locations  {

    private String title;
    private String address;
    private String description;
    private Double budget;
    private Double lt;
    private Double ln;
    private String image;
    // Default constructor is important!
    public Locations() {

    }
    public Locations(String title, String address, String description, Double budget, String image) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.budget = budget;
        this.image = image;
    }

    public Locations(String title, String address, String description, Double budget, Double lt, Double ln, String image) {
        this.title = title;
        this.address = address;
        this.description = description;
        this.budget = budget;
        this.lt = lt;
        this.ln = ln;
        this.image = image;
    }

    public Double getLt() {
        return lt;
    }

    public void setLt(Double lt) {
        this.lt = lt;
    }

    public Double getLn() {
        return ln;
    }

    public void setLn(Double ln) {
        this.ln = ln;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
