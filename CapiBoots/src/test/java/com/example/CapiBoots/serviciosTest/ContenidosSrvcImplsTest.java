//package com.example.CapiBoots.serviciosTest;
//
//import com.example.CapiBoots.modelos.Categorias;
//import com.example.CapiBoots.modelos.Contenidos;
//import com.example.CapiBoots.repositorios.CategoriasRepositorios;
//import com.example.CapiBoots.repositorios.ContenidosRepositorio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ContenidosSrvcImplsTest implements ifxContenidosSrvcTest {
//    @Autowired
//    public ContenidosRepositorio contenidoRepo;
//
//    @Autowired
//    public CategoriasRepositorios catRepo;
//
//    @Override
//    public Optional<Contenidos> buscarContenidoId(Long id) {
//        return contenidoRepo.findById(id);
//    }
//
//    @Override
//    public Contenidos guardarContenido() {
//        return null;
//    }
//
//    @Override
//    public Contenidos eliminarContenido() {
//        return null;
//    }
//
//    @Override
//    public Contenidos actualizarContenido() {
//        return null;
//    }
//
//    @Override
//    public Contenidos buscarContenido() {
//        return null;
//    }
//
//
//
//    //Listar
//    @Override
//    public List<Contenidos> listaCont() {
//        return contenidoRepo.findAll();
//    }
//    //Guardar
//    @Override
//    public Contenidos guardar(Contenidos contenido) {
//        return contenidoRepo.save(contenido);
//    }
//
//    @Override
//    public Object listaNovedades() {
//        return null;
//    }
//
//    //Borrar
//    public void borrar(Long id){
//        contenidoRepo.deleteById(id);
//    }
//
//    public Object listaPend() {
//        return null;
//    }
//
//    //Búsqueda
//    @Override
//    public List<Contenidos> buscaCont (String keyword){
//        List<Contenidos> lista = new ArrayList<>();
//        if (keyword != null){
//            lista = contenidoRepo.buscarTodos(keyword);
//            return lista;
//    }
//        return contenidoRepo.findAll();
//    }
//   /* public List<Contenidos> filtroCategoria (String keyword){
//        List<Contenidos> lista = new ArrayList<>();
//        if (keyword != null) {
//            lista = contenidoRepo.buscarPorCat(keyword);
//            return lista;
//        }
//        return contenidoRepo.findAll();
//    }*/
//
//   //Pendientes
//    //TODO: Introducir String en direccion href
//    public Optional<Long> pendientes (Long id){
//        //Contenidos contNuevo = new Contenidos();
//        //buscarContenidoId(id);
//        // Como utilizamos el operador ternario (también llamado Edison) y utilizamos Optional (que puede ser nulo),
//        // podemos reducir las siguientes líneas a una sola
//        /*
//        Optional urlPendiente;
//        if (contEmpezado(true) && contTerminado(false)) {
//            urlPendiente = "http://localhost:8080/pendientes" + id;
//        } else {
//             urlPendiente = null;
//        }
//        return urlPendiente;
//        */
//        return Optional.ofNullable(contEmpezado(true) && contTerminado(false) ? id : null);
//    }
//
//    //Novedades
//    public Contenidos novedades (Contenidos contNuevo) throws InterruptedException {
//        //Buscamos la categoría novedades
//        Categorias cat2 = catRepo.findByNombre("Novedades");
//        //Creamos una lista de categorías
//        List<Categorias> cat;
//        //Asignamos las categorías actuales del contenido a esta lista vacía
//        cat = contNuevo.getCategorias();
//        //Añadimos la categoría Novedades a esta lista
//        cat.add(cat2);
//        //Asignamos la lista ampliada a la lista de categorías del contenido nuevo
//        contNuevo.setCategorias(cat);
//        //Esperamos 7 días (60480 segundos)
//
//        //Quitamos la categoría novedades del contenido
//
//        return contNuevo;
//    }
//
//    //Campos extra
//    public Boolean contEmpezado(boolean a) {
//
//
//        return a;
//    }
//    public Boolean contTerminado(boolean b) {
//        return b;
//    }
//}
