package com.example.CapiBoots.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.Text;

import java.math.BigInteger;

@Entity
@Table(name="comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comentarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //@ManyToOne
    //@JoinColumn(name= "idContenido")
    private Long idContenido;

    @Column(name = "texto",columnDefinition = "TEXT(300)")
    private String texto;

    @Column(name ="idTipo",columnDefinition = "INT")
    private Integer idTipo;


}