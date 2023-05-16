//package com.example.CapiBoots.serviciosTest;
//
//import com.example.CapiBoots.modelos.Logro;
//import com.example.CapiBoots.repositorios.LogroRepositorio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class LogroSrvcImplsTest implements ifxLogroSrvcTest {
//
//    @Autowired
//    public LogroRepositorio logroRepo;
//
//    @Override
//    public Optional<Logro> buscarLogroId(Long id) {
//        return logroRepo.findById(id);
//    }
//
//    @Override
//    public Logro buscarPorId(Long id) {
//        return logroRepo.findById(id).orElse(null);
//    }
//
//
//    //Listar
//    @Override
//    public List<Logro> listaLogro() {
//        return logroRepo.findAll();
//    }
//
//    @Override
//    public Logro guardarLogro(Logro logro) {
//        return null;
//    }
//
//
//    //Guardar
//    @Override
//    public Logro guardar(Logro logro) {
//        return logroRepo.save(logro);
//    }
//
//    @Override
//    public Logro crear(Logro logro) {
//        return null;
//    }
//
//    //Borrar
//    public void borrar(Long id){
//        logroRepo.deleteById(id);
//    }
//}
