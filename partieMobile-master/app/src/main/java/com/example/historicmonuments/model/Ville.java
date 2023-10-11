package com.example.historicmonuments.model;

import java.io.Serializable;

public class Ville implements Serializable {
    private int id;
    private String nom,pays;
    private Double longitude,lattitude;

    public Ville() {
    }

    public Ville(int id, String nom, String pays, Double longitude, Double lattitude) {
        this.id = id;
        this.nom = nom;
        this.pays = pays;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public Ville(String nom, String pays, Double longitude, Double lattitude) {
        this.nom = nom;
        this.pays = pays;
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

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
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

    @Override
    public String toString() {
        return "Ville{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", pays='" + pays + '\'' +
                ", longitude=" + longitude +
                ", lattitude=" + lattitude +
                '}';
    }
}
