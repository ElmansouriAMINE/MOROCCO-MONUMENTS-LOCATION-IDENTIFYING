package com.example.backendTp.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name="Ville")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String nom,pays;
    private Double longitude,lattitude;
    @OneToMany(mappedBy = "ville")
    private Set<Zone> zones;
    @OneToMany(mappedBy = "ville")
    private Set<Monument> monuments;

    public Ville(int id){
        this.id=id;
    }



}
