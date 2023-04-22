package com.example.CapiBoots.controladores;

import com.example.CapiBoots.modelos.Usuario;
import com.example.CapiBoots.repositorios.UsuarioRepositorio;
import com.example.CapiBoots.servicios.UsuarioSrvcImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;

import java.util.List;
import java.util.Optional;

@Controller
//@RequestMapping("/usuario")
public class UsuarioCtrl {

    @Autowired
    private UsuarioSrvcImpls usuSrvc;



    @GetMapping({"","/"})
    public String inicio(Model modelo) {
        modelo.addAttribute("titulo", "Página de inicio de relaciones N:M");
        return "inicio";
    }

    @GetMapping("/acceso")
    public String Acceso(Model modelo) {
        modelo.addAttribute("titulo", "Página de acceso");
        return "login";
    }

    @PostMapping("/acceso")
    public String comprobarAcceso(Model modelo, @RequestParam("usuario") String usu, @RequestParam("clave") String clave){
        String texto = "Hola " + usu + " tu clave es " + clave + ".";
        modelo.addAttribute("texto", texto);

        return"exitoLogin";    }


    @GetMapping("/listausus")
    public String listaUsus(Model modelo){
        modelo.addAttribute("listausuarios", usuSrvc.listaUsus());
        return "listausus";
    }
    @GetMapping("/buscarusus")
    public String buscarUsus(@PathVariable String keyword , Model modelo){
        List<Usuario> listausu = usuSrvc.buscaUsus(keyword);
        modelo.addAttribute("listausuarios", listausu);
        return "listausus";
    }

    @GetMapping("/usuario-id")
    public String UsuPorId(@PathVariable Long id, Model modelo){
        modelo.addAttribute("idusu", usuSrvc.buscaId(id));
        return "usuario";   //Buscar en búsqueda con el filtro de "Usuarios"
    }

    @GetMapping("/registro")
    public String Registro(Model modelo){
        modelo.addAttribute("titulo", "CapiBoots");
        modelo.addAttribute("titulo2", "Esta es mi aplicación CapiBoots");
        return "registro";
    }

    @PostMapping("/registro")
    public String alta(Model modelo){
        return "exito";
    }

    @GetMapping("/suscripcion")
    public String Suscripcion (Model modelo) {
        // Buscar el registro en la BBDD
        // colocar los datos en el parámetro que pasas a la vista de Thymeleaf
        modelo.addAttribute("titulo", "Estás viendo tus datos de suscripción");
        // devuelve el archivo html con la vista
        return "suscripcion";
    }

    @GetMapping("/lista-amigos")
    public String ListaAmigos (Model modelo) {
        modelo.addAttribute("titulo", "Lista de amigos");
        return "listaAmigos";
    }

    @GetMapping("/ajustes")
    public String Ajustes(Model modelo) {
        modelo.addAttribute("titulo", "Ajustes");
        return "ajustes";
    }
    @GetMapping("/logros")
    public String Logros(Model modelo) {
        modelo.addAttribute("titulo", "Logros");
        return "logros";
    }

    //Crear, Guardar, Borrar y Editar

    @GetMapping("/usuario/nuevo-usuario")
    public String nuevoUsu(Model modelo){
        modelo.addAttribute("usuario", new Usuario());
//        modelo.addAttribute("fragmentName", "fragment-customer-form");
        return "nuevo-usuario";
    }

    @PostMapping("/usuario/guardar")
    public String guardar(Usuario usu){
        usuSrvc.guardar(usu);
        return "redirect:/listausus";
    }

    @GetMapping("/usuario/borrar/{id}")
    public String borrar(@PathVariable Long id){
        usuSrvc.borrar(id);
        return "redirect:/listausus";
    }

    @GetMapping("/usuario/editar/{id}")
    public String editar(@PathVariable Long id, Model modelo){
        Optional<Usuario> usuOpt = usuSrvc.buscaId(id);
        if(usuOpt.isPresent()){
            modelo.addAttribute("usuario", usuOpt.get());
        }
        else{
            // Si el cliente no existe, redirigir a una página de error o mostrar un mensaje de error
            return "error";
        }
        return "nuevo-usuario";
    }




}
