package com.example.CapiBoots.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Series")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
    public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name ="nombre",columnDefinition = "VARACHAR(45)")
    private String nombre;

    @Column(name= "descripción",columnDefinition = "VARCHAR(225)")
    private String descripción;
}