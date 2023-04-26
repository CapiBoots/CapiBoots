package com.example.CapiBoots.controladores;

import com.example.CapiBoots.modelos.Accesos;
import com.example.CapiBoots.servicios.AccesosSrvcImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AccesosCtrl {
    @Autowired
    public AccesosSrvcImpls accessSrvc;

    @GetMapping("/lista-accesos")
    public String listaAccesos (Model modelo){
        modelo.addAttribute("listaaccesos", accessSrvc.listaAcces());
        return "lista-accesos";
    }
    @GetMapping("/acceso-id")
    public String accesoId (@PathVariable Long id, Model modelo){
        modelo.addAttribute("acceso_id", accessSrvc.buscaId(id));
        return "acceso-id";
    }

    @GetMapping("/lista-pendientes/{usu}")
    public String listaPdtes(@PathVariable String usu, Model modelo){
       modelo.addAttribute("pendientes",accessSrvc.buscaPendientes(Long.parseLong(usu)));
       return "/listas/lista-pendientes";
    }
}