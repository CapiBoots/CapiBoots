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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "fecha_inicio", columnDefinition = "DATETIME")
    @Convert(converter = TimeConverter.class)
    protected LocalDateTime fecha_inicio;

    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idContenido", nullable = false)
    private Contenidos contenido;

    @Convert(converter = TimeConverter.class)
    @Column(name = "fecha_fin", columnDefinition = "DATETIME")
    private LocalDateTime fecha_fin;

    private Boolean terminado=false;
}
