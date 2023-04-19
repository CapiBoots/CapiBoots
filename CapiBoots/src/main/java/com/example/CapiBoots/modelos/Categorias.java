package com.example.CapiBoots.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name="Categorias")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Categorias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="nombre")
    private String nombre;
    @ManyToOne
    @JoinColumn(name="idMadre", nullable = false)
    private Categorias idmadre;
    @Column(name="Descripcion", columnDefinition = "TEXT", length = 1024)
    private String descripcion;
    @Column(name="Imagen", columnDefinition = "VARCHAR(100)")
    private String imagen;

    @ManyToMany(mappedBy = "categorias")
    private List<Contenidos> contenidos;

}
