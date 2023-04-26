package com.example.CapiBoots.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="Contenidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contenidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="nombre", columnDefinition = "VARCHAR(225)")
    private String nombre;
    @ManyToOne
    @JoinColumn(name="idserie", nullable = true)
    private Series idserie;
    @ManyToOne
    @JoinColumn(name="idtemporada", nullable = true)
    private Temporada idtemporada;
    @Column(name="FechaAlta", columnDefinition = "DATETIME default NOW()")
    private Date fechaAlta;

    @ManyToMany // Many to Many entre Contenidos y Categorias
    @JoinTable(
            name = "contenidos_categorias",
            joinColumns = @JoinColumn(name = "id_contenido"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria"))
    private List<Categorias> categorias;




}
