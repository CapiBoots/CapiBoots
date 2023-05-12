package com.example.CapiBoots.controladores;

import com.example.CapiBoots.modelos.Accesos;
import com.example.CapiBoots.modelos.Contenidos;
import com.example.CapiBoots.modelos.Series;
import com.example.CapiBoots.servicios.AccesosSrvcImpls;
import com.example.CapiBoots.servicios.ContenidosSrvcImpls;
import com.example.CapiBoots.servicios.SeriesSrvcImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class ContenidosCtrl {

    @Autowired
    private ContenidosSrvcImpls contenidosSrvc;

    @Autowired
    private SeriesSrvcImpls serieSrvc;

    @Autowired
    private AccesosSrvcImpls accesoSrvc;

    @GetMapping("/guardarContenido")
    public String guardarContenido(Model modelo) {
        modelo.addAttribute("guardarContenidos", contenidosSrvc.guardarContenido());
        return "guardarContenidos";
    }

    @GetMapping("/eliminarContenido")
    public String eliminarContenido(Model modelo) {
        modelo.addAttribute("eliminarContenidos", contenidosSrvc.eliminarContenido());
        return "eliminarContenidos";
    }

    @GetMapping("/actualizarContenido")
    public String actualizarContenido(Model modelo) {
        modelo.addAttribute("actualizarContenidos", contenidosSrvc.actualizarContenido());
        return "actualizarContenidos";
    }

    @GetMapping("/buscarContenido")
    public String buscarContenido(Model modelo) {
        modelo.addAttribute("buscarContenidos", contenidosSrvc.buscarContenido());
        return "buscarContenidos";
    }

    @GetMapping("/showbox")
    public String Showbox(Model modelo) {
        modelo.addAttribute("titulo", "Showbox");
        return "Showbox";
    }

    @GetMapping("/bookbox")
    public String bookbox(Model modelo) {

        modelo.addAttribute("titulo", "BookBox");
        return "bookbox";
    }

    @GetMapping("/moviebox")
    public String showbox(Model modelo) {

        modelo.addAttribute("titulo", "Moviebox");
        return "moviebox";
    }

    @GetMapping("/favoritos")
    public String favoritos(Model modelo) {

        modelo.addAttribute("titulo", "Favoritos");
        return "favoritos";
    }

    //ADMINISTRADOR//
    @GetMapping("/gestion")
    public String gestion(Model modelo) {

        modelo.addAttribute("titulo", "Gestion");
        return "gestion";

    }

    @GetMapping("/gestion/libros")
    public String gestionLibros(Model modelo) {

        modelo.addAttribute("titulo", "GestionLibros");
        return "gestionLibros";

    }

    @GetMapping("/gestion/pelis")
    public String gestionPelis(Model modelo) {

        modelo.addAttribute("titulo", "GestionPelis");
        return "gestionPelis";

    }

    @GetMapping("/gestion/series")
    public String gestionSeries(Model modelo) {

        modelo.addAttribute("titulo", "GestionSeries");
        return "gestionSeries";

    }


    //---------------------------------

        //Búsqueda

    @GetMapping("/busqueda")
    public String busqueda(@Param("keyword") String keyword, Model modelo) {
        List<Series> buscaseri = serieSrvc.buscaSeri(keyword);
        List<Contenidos> buscacont = contenidosSrvc.buscaCont(keyword);
        modelo.addAttribute("listaseries", buscaseri);
        modelo.addAttribute("listaContenidos", buscacont);
        return "busqueda";
    }

       /* //Filtro de Categorias
        @GetMapping("/busqueda/categoria")
        public String filtroCat(@PathVariable ("keyword") String keyword, Model modelo) {

            List<Contenidos> buscacont = contenidosSrvc.filtroCategoria(keyword);
            modelo.addAttribute("listacontenidos", contenidosSrvc.listaCont());
            modelo.addAttribute("listacontenidos", buscacont);
            return "busqueda";
        }*/


    //Lista de contenidos
    @GetMapping("/contenido/lista-contenidos")
    public String listaContenidos(Model modelo) {
        modelo.addAttribute("listaContenidos", contenidosSrvc.listaCont());
        return "/listas/lista-contenidos";
    }

    //Crear, guardar, borrar y editar
    @GetMapping("/contenido/nuevo-contenido")
    public String crearContenido(Model modelo) {
        modelo.addAttribute("contenido", new Contenidos());
        return "/forms/nuevo-contenido";
    }

    @PostMapping("/contenido/guardar")
    public String guardarContenido(Contenidos contenido) {
        contenidosSrvc.guardar(contenido);
        return "redirect:/contenido/lista-contenidos";
    }

    @GetMapping("/contenido/borrar/{id}")
    public String borrarContenido(@PathVariable Long id) {
        contenidosSrvc.borrar(id);
        return "redirect:/contenido/lista-contenidos";
    }

    @GetMapping("/contenido/editar/{id}")
    public String editarContenido(@PathVariable Long id, Model modelo) {
        Optional<Contenidos> contOptional = contenidosSrvc.buscarContenidoId(id);
        if (contOptional.isPresent()) {
            modelo.addAttribute("contenido", contOptional.get());
        } else {
            return "error";
        }
        return "forms/nuevo-contenido";
    }

    @GetMapping("/contenido/lista-pendientes")
    public String listaPendientes(Model modelo) {
        modelo.addAttribute("listaPendientes", contenidosSrvc.listaPend());
        return "/listas/lista-pendientes";
    }

    // Marcar como visualizado
    @GetMapping("/pendientes/{id}")
    public String pendientes(@PathVariable Long id, Model modelo) {
        modelo.addAttribute("pendientes", contenidosSrvc.pendientes(id));
        return "redirect:/contenido/lista-contenidos";
    }

    @GetMapping("/reproducir/{id}")
    public String reproducir(@PathVariable Long id, Model modelo) {
        contenidosSrvc.buscarContenidoId(id);
        modelo.addAttribute("contenido", id);
        return "reproductor";
    }

    @GetMapping("/contenido/{id}")
    public String contPpal (@PathVariable Long id, Model modelo){
        Optional<Contenidos> cont = contenidosSrvc.buscarContenidoId(id);
        modelo.addAttribute("contenido", cont.get());
        return "contenido";
    }


}
