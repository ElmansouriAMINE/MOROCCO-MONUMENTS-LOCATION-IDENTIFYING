package com.example.historicmonuments.model;

import java.io.Serializable;

public class Monument implements Serializable {
    private int id;
    private String nom;
    private Double longitude,lattitude;
    private Ville ville;
    private String photo;

    public Monument(String nom, Double longitude, Double lattitude, Ville ville, String photo) {
        this.nom = nom;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.ville = ville;
        this.photo = photo;
    }
    public Monument(String nom, Double longitude, Double lattitude, String photo) {
        this.nom = nom;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.photo = photo;
    }

    public Monument() {
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Monument{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", longitude=" + longitude +
                ", lattitude=" + lattitude +
                ", ville=" + ville +
                ", photo='" + photo + '\'' +
                '}';
    }
}
