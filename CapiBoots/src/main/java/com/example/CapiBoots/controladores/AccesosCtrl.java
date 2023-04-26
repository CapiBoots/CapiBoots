package com.example.CapiBoots.controladores;

import com.example.CapiBoots.modelos.Accesos;
import com.example.CapiBoots.modelos.Contenidos;
import com.example.CapiBoots.servicios.AccesosSrvcImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AccesosCtrl {
    @Autowired
    public AccesosSrvcImpls accessSrvc;

    @GetMapping("/lista-accesos")
    @ResponseBody
    public List<Accesos> listaAccesos (Model modelo){
        List<Accesos> lista = accessSrvc.listaAcces();
        modelo.addAttribute("listaaccesos", accessSrvc.listaAcces());
        return lista;
    }

    @GetMapping("/acceso-id")
    public String accesoId (@PathVariable Long id, Model modelo){
        modelo.addAttribute("acceso_id", accessSrvc.buscaId(id));
        return "acceso-id";
    }

    @GetMapping("/lista-pendientes/{usu}")
    //public List<Accesos> listaPdtes(@PathVariable String usu, Model modelo){  // Para ver los resultados como JSON
    public String listaPdtes(@PathVariable String usu, Model modelo){           // Para ver los resultados como html
        List<Accesos> lista = accessSrvc.buscaPendientes(Long.parseLong(usu));
        modelo.addAttribute("pendientes",lista);
        //return lista;         // Para ver los resultados como JSON
        return "/listas/lista-pendientes";      // Para ver los resultados como html
    }
}