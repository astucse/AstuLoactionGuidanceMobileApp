package com.k97h.khalil.astulocationguidance.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class LocationData implements Serializable {

    private int id;
    private String name;
    private String description;
    private Bitmap image;
    private double latitude;
    private double longitude;



    public LocationData(String name, int id, String description, Bitmap image, double latitude, double longitude) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.image = image;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Bitmap getImage() {
        return image;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
