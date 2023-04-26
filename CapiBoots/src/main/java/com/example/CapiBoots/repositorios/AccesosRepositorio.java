package com.example.CapiBoots.repositorios;

import com.example.CapiBoots.modelos.Accesos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccesosRepositorio extends JpaRepository<Accesos, Long> {

    @Query("SELECT a FROM Accesos a WHERE a.idUsuario.id=?1 AND NOT a.terminado")
    List<Accesos> buscarAccesosPendientes(Long usu);
}
