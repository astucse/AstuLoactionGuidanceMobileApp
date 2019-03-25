package com.k97h.khalil.astulocationguidance.models;

import android.graphics.Bitmap;

import java.io.Serializable;

public class LocationData implements Serializable {

    private int id;
    private String name;
    private String description;
    private byte[] image;
    private double latitude;
    private double longitude;



    public LocationData(int id, String name, String description, byte[] image, double latitude, double longitude) {
        this.id = id;
        this.name = name;
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

    public byte[] getImage() {
        return image;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
