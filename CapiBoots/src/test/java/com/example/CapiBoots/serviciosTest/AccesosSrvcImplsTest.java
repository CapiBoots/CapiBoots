//package com.example.CapiBoots.serviciosTest;
//
//import com.example.CapiBoots.modelos.Accesos;
//import com.example.CapiBoots.modelos.Contenidos;
//import com.example.CapiBoots.repositorios.AccesosRepositorio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class AccesosSrvcImplsTest implements ifxAccesosSrvcTest {
//    @Autowired
//    public AccesosRepositorio accessrepo;
//
//    @Override
//    public Accesos buscaId(Long id) {
//        return accessrepo.findById(id).orElse(null);
//    }
//
//    public Accesos guardar(Accesos acceso) {
//        return accessrepo.save(acceso);
//    }
//
//    // Devuelve el último acceso de un usuario a un contenido
//    public Optional<Accesos> buscaUltimoAcceso(Long usu, Long cont){
//        // obtenemos la lista de todos los accesos del usuario al contenido indicado
//        List<Accesos> lista = accessrepo.buscarAccesos(usu, cont);
//        // Obtenemos el último de la lista como Optional, ya que puede no existir (y ser nulo)
//        Optional<Accesos> ult = Optional.ofNullable(lista.get(lista.size()-1));
//        return ult;
//    }
//
//    @Override
//    public Optional<List<Accesos>> listaAcces() {
//        return Optional.ofNullable(accessrepo.findAll());
//    }
//
//    @Override
//    public List<Contenidos> buscaPendientes(Long usu) {
//        return accessrepo.buscarPendientes(usu);
//    }
//
//}
