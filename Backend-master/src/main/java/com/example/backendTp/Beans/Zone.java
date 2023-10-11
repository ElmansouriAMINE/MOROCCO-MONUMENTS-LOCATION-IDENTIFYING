package com.example.backendTp.Beans;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Zone")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Zone {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private Double longitude,lattitude;
    @JsonBackReference
    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "ville_id", referencedColumnName="id")
    private Ville ville;

//    {
//        "id": 17,
//            "nom": "Zone13",
//            "longitude": 25.888,
//            "lattitude": 25.9992,
//            "ville":{
//        "id":1
//    }
//
//    }

}
