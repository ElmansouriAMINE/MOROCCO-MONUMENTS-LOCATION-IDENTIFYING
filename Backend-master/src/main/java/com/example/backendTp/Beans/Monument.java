package com.example.backendTp.Beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Monument")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Monument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String nom;
    private Double longitude,lattitude;

    @ManyToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "ville_id", referencedColumnName="id")
    private Ville ville;

    private String photo;


}
