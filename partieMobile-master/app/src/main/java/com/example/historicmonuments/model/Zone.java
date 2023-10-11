package com.example.historicmonuments.model;

import java.io.Serializable;

public class Zone implements Serializable {
    private int id;
    private String nom;
    private Double longitude,lattitude;

    public Zone() {
    }

    public Zone(int id, String nom, Double longitude, Double lattitude) {
        this.id = id;
        this.nom = nom;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public Zone(String nom, Double longitude, Double lattitude) {
        this.nom = nom;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLattitude() {
        return lattitude;
    }

    public void setLattitude(Double lattitude) {
        this.lattitude = lattitude;
    }
}
