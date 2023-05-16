//package com.example.CapiBoots.serviciosTest;
//
//import com.example.CapiBoots.modelos.Temporada;
//import com.example.CapiBoots.repositorios.TemporadaRepositorio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TemporadaSrvcImplsTest implements ifxTemporadaSrvcTest {
//
//    @Autowired
//    public TemporadaRepositorio temporepo;
//
//
//    @Override
//    public Optional<Temporada> buscaId(Long id) {
//        return temporepo.findById(id);
//    }
//
//    //Listar
//    @Override
//    public List<Temporada> listaTempo() {
//        return temporepo.findAll();
//    }
//
//    //Guardar y Borrar porque Crear/Editar se definen en el controlador.
//
//    @Override
//    public Temporada guardar(Temporada tempo) {
//        return temporepo.save(tempo);
//    }
//
//    public void borrar(Long id) {
//        temporepo.deleteById(id);
//    }
//}
