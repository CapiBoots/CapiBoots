//package com.example.CapiBoots.serviciosTest;
//
//import com.example.CapiBoots.modelos.Series;
//import com.example.CapiBoots.repositorios.SeriesRepositorio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class SeriesSrvcImplsTest implements ifxSeriesSrvcTest {
//
//    @Autowired
//    public SeriesRepositorio serierepo;
//
//    @Override
//    public Optional<Series> buscaId(Long id) {
//        return serierepo.findById(id);
//    }
//
//    //Buscador por palabra clave
//    @Override
//    public List<Series> buscaSeri(String keyword) {
//        List<Series> lista = new ArrayList<>();
//        if (keyword != null) {
//            lista = serierepo.buscarTodos(keyword);
//            return lista;
//        }
//        return serierepo.findAll();
//    }
//
//    //Listar
//    @Override
//    public List<Series> listaSeri() {
//        return serierepo.findAll();
//    }
//
//    //Guardar y Borrar porque Crear/Editar se definen en el controlador.
//
//    @Override
//    public Series guardar(Series seri) {
//        return serierepo.save(seri);
//    }
//
//    public void borrar(Long id) {
//        serierepo.deleteById(id);
//    }
//
//}
