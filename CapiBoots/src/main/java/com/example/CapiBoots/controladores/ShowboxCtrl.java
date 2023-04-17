package com.example.CapiBoots.controladores;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowboxCtrl {

    @GetMapping ("/showbox")
    public String Showbox(Model modelo){
        modelo.addAttribute("titulo", "Showbox");
        return "Showbox";
    }

    @GetMapping("/novedades")
    public String novedades(Model model) {
        model.addAttribute("titulo", "Novedades");
        return "novedades";
    }

    @GetMapping("/mis-peliculas")
    public String misPeliculas(Model model) {
        model.addAttribute("titulo", "Mis peliculas");
        return "mis-peliculas";
    }

    @GetMapping("/seguir-leyendo")
    public String seguirViendo(Model model) {
        model.addAttribute("titulo", "Seguir viendo");
        return "seguir-viendo";
    }

    @GetMapping("/generos")
    public String generos(Model model) {
        model.addAttribute("titulo", "Generos");
        return "generos";
    }
}