package com.example.CapiBoots.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/titulo")
public class BookBoxCtrl {

    @GetMapping ("/bookbox")
    public String Bookbox(Model modelo){
        modelo.addAttribute("titulo", "Bookbox");
        return "bookbox";
    }

    @GetMapping("/novedades")
    public String novedades(Model model) {
        model.addAttribute("titulo", "Novedades");
        return "novedades";
    }

    @GetMapping("/mis-libros")
    public String misLibros(Model model) {
        model.addAttribute("titulo", "Mis libros");
        return "mis-libros";
    }

    @GetMapping("/seguir-leyendo")
    public String seguirLeyendo(Model model) {
        model.addAttribute("titulo", "Seguir leyendo");
        return "seguir-leyendo";
    }

    @GetMapping("/generos")
    public String generos(Model model) {
        model.addAttribute("titulo", "Generos");
        return "generos";
    }

}