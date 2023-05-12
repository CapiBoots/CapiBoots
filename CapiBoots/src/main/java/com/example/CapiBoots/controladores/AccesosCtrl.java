package com.example.CapiBoots.controladores;

import com.example.CapiBoots.modelos.Accesos;
import com.example.CapiBoots.modelos.Usuario;
import com.example.CapiBoots.repositorios.AccesosRepositorio;
import com.example.CapiBoots.servicios.AccesosSrvcImpls;
import com.example.CapiBoots.servicios.UsuarioSrvcImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.http.HttpResponse;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class AccesosCtrl {
    @Autowired
    public AccesosSrvcImpls accessSrvc;
    @Autowired
    private UsuarioSrvcImpls usuSrvc;
    /* Si ya utilizamos la clase <AccesosSrvcImpls>, esta no debería ser necesaria, puesto que <AccesosSrvcImpls>
        publica -pone a disposición de los controladores- lo que corresponda de <AccesosRepositorio>
    @Autowired
    private AccesosRepositorio accesosRepositorio;
    */

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

    @GetMapping("/lista-pendientes/{id}")
    public String listaPdtes(@PathVariable Long id, Model modelo){
       modelo.addAttribute("pendientes",accessSrvc.buscaPendientes(id));
       return "/listas/lista-pendientes";
    }

    @GetMapping("/empezar/{id}")
    @ResponseStatus(value = HttpStatus.OK)  // indica que la respuesta tendrá un status OK y no hay que devolver nada
    public void empezar(@PathVariable Long id, Principal principal, Model modelo){
        String usuID = principal.getName();
        Usuario usu = usuSrvc.buscaPorNombre(usuID);

        // buscar el último acceso del usuario al contenido
        Optional<Accesos> ultAccesoOpt = accessSrvc.buscaUltimoAcceso(usu.getId(),id);

        // Si no hay ninguno, es la primera vez que el usuario empieza el contenido y hay que marcarlo. Para ello,
        // añadimos un nuevo registro a la tabla de accesos
        accessSrvc.buscaUltimoAcceso(usu.getId(),id).ifPresentOrElse(
                acc -> {        // Ya existe un acceso previo de ese usuario a ese contenido. Se inicializa el reg.
                    if(acc.getTerminado()) {
                        acc.setTerminado(Boolean.FALSE);
                        acc.setFecha_fin(null);
                        acc.setFecha_inicio(LocalDateTime.now());
                        accessSrvc.guardar(acc);
                    }
                },
                () -> {         // No existe acceso previo. Se crea un nuevo registro.
                    Accesos nuevoAcceso = new Accesos();
                    nuevoAcceso.setUsuario(usu);
                    accessSrvc.guardar(nuevoAcceso);
                }
        );
    }

    @GetMapping("/terminar/{id}")
    public String terminar(@PathVariable Long id, Principal ppal, Model modelo) {

        // localizamos el usuario
        String usuID = ppal.getName();
        Usuario usu = usuSrvc.buscaPorNombre(usuID);
        // buscar el último acceso del usuario al contenido
        Optional<Accesos> ultAccesoOpt = accessSrvc.buscaUltimoAcceso(usu.getId(),id);
        accessSrvc.buscaUltimoAcceso(usu.getId(),id).ifPresentOrElse(
                acc -> {
                    acc.setFecha_fin(LocalDateTime.now());
                    acc.setTerminado(Boolean.TRUE);
                    //resp = new ResponseEntity<Void>(HttpStatus.OK);
                    modelo.addAttribute("mensaje","El contenido ha sido marcado como terminado.");
                    modelo.addAttribute("status","OK");

                },
                () -> {
                    modelo.addAttribute("mensaje", "Contenido no hallado.");
                    modelo.addAttribute("status","No hallado");
                }
        );
        return "reproductor";
    }
}