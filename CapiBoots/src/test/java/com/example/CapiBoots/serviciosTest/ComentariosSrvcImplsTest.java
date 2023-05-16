//package com.example.CapiBoots.serviciosTest;
//
//import com.example.CapiBoots.modelos.Comentarios;
//import com.example.CapiBoots.modelos.Contenidos;
//import com.example.CapiBoots.repositorios.ComentariosRepositorio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ComentariosSrvcImplsTest implements ifxComentariosSrvcTest {
//    @Autowired
//    public ComentariosRepositorio comentrepo;
//
//    @Override
//    public Optional<Contenidos> buscarComentario(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Contenidos guardarComentario() {
//        return null;
//    }
//
//    @Override
//    public Contenidos eliminarComentario() {
//        return null;
//    }
//
//    @Override
//    public Contenidos actualizarComentario() {
//        return null;
//    }
//
//    @Override
//    public Contenidos buscarComentario() {
//        return null;
//    }
//
//    @Override
//    public Optional<Comentarios> buscarComentarioId(Long id) {
//        return Optional.empty();
//    }
//
//    @Override
//    public Optional<Comentarios> buscaId(Long id) {
//        return comentrepo.findById(id);
//    }
//    //Listar
//    @Override
//    public List<Comentarios> listaCom() {
//        return comentrepo.findAll();
//    }
//
//    @Override
//    public Contenidos guardar(Contenidos contenido) {
//        return null;
//    }
//
//    //Guardar
//    @Override
//    public Comentarios guardar(Comentarios comentario) {
//        return comentrepo.save(comentario);
//    }
//    //Borrar
//    public void borrar(Long id){
//        comentrepo.deleteById(id);
//    }
//
//
//}
