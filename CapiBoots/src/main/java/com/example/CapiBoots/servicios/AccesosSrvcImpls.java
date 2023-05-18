package com.example.CapiBoots.servicios;

import com.example.CapiBoots.modelos.Accesos;
import com.example.CapiBoots.modelos.Contenidos;
import com.example.CapiBoots.modelos.Usuario;
import com.example.CapiBoots.repositorios.AccesosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccesosSrvcImpls implements ifxAccesosSrvc{
    @Autowired
    public AccesosRepositorio accessrepo;

    @Override
    public Accesos buscaId(Long id) {
        return accessrepo.findById(id).orElse(null);
    }

    public Accesos guardar(Accesos acceso) {
        return accessrepo.save(acceso);
    }

    // Devuelve el último acceso de un usuario a un contenido
    public Optional<Accesos> buscaUltimoAcceso(Long usu, Long cont){
        // obtenemos la lista de todos los accesos del usuario al contenido indicado
        if(accessrepo.buscarAccesos(usu, cont).isPresent()) {
            List<Accesos> lista = accessrepo.buscarAccesos(usu, cont).get();
            if (lista.size() > 0) {
                return Optional.of(lista.get(lista.size()-1));
            };
        }
        return Optional.ofNullable(null);
    }

    @Override
    public Optional<List<Accesos>> listaAcces() {
        return Optional.ofNullable(accessrepo.findAll());
    }

    @Override
    public List<Contenidos> buscaPendientes(Long usu) {
        return accessrepo.buscarPendientes(usu);
    }

    public List<Contenidos> pelisPendientes(Long usu) {
        List<Contenidos> pdtes = this.buscaPendientes(usu);
        List<Contenidos> pelispdtes = pdtes.stream().filter(cont -> cont.getTipo().equals("Pelicula")).collect(Collectors.toList());
        return pelispdtes;
    }
    public List<Contenidos> librosPendientes(Long usu) {
        return this.buscaPendientes(usu).stream().filter(cont -> cont.getTipo().equals("libro")).collect(Collectors.toList());
    }
    public List<Contenidos> capitulosPendientes(Long usu) {
        return this.buscaPendientes(usu).stream().filter(cont -> cont.getTipo().equals("Capítulo")).collect(Collectors.toList());
    }

}
