package com.example.CapiBoots.modelos;

import com.example.CapiBoots.modelos.metodos.TimeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="accesos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Accesos{

    @Id
    @Column(name = "fecha_inicio", nullable = false, columnDefinition = "DATETIME default NOW()")
    @Convert(converter = TimeConverter.class)
    protected LocalDateTime fecha_inicio;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "idContenido", nullable = false)
    private Contenidos idContenido;

    @Convert(converter = TimeConverter.class)
    @Column(name = "fecha_fin", columnDefinition = "DATETIME")
    private LocalDateTime fecha_fin;

    @Column(columnDefinition = "Boolean default false")
    private Boolean terminado=false;
}
