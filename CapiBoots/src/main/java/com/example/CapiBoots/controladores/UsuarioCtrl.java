package com.example.CapiBoots.controladores;

import com.example.CapiBoots.modelos.Accesos;
import com.example.CapiBoots.modelos.Usuario;
import com.example.CapiBoots.servicios.AccesosSrvcImpls;
import com.example.CapiBoots.servicios.UsuarioSrvcImpls;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UsuarioCtrl {

    @Autowired
    private UsuarioSrvcImpls usuSrvc;

    @Autowired
    private AccesosSrvcImpls accessSrvc;



    @GetMapping({"","/"})
    public String inicio(Model modelo) {
        List<Accesos> pdtes = accessSrvc.buscaPendientes(2L);
        modelo.addAttribute("pendientes", pdtes);
        modelo.addAttribute("titulo", "Página de inicio de relaciones N:M");
        return "/inicio";
    }

    //Login
    @GetMapping("/acceso")
    public String Acceso(Model modelo) {
        modelo.addAttribute("titulo", "Página de acceso");
        return "/administrarUsuario/login";
    }

    @PostMapping("/acceso")
    public String comprobarAcceso(Model modelo, @RequestParam("usuario") String usu, @RequestParam("clave") String clave){
        String texto = "Hola " + usu + " tu clave es " + clave + ".";
        modelo.addAttribute("texto", texto);

        return"/administrarUsuario/exitoLogin";    }

    @GetMapping("/usuario-id/{id}")
    public String UsuPorId(@PathVariable Long id, Model modelo){
        modelo.addAttribute("idusu", usuSrvc.buscaId(id));
        return "usuario";   //Buscar en búsqueda con el filtro de "Usuarios"
    }

    @GetMapping("/registro")
    public String Registro(Model modelo){
        modelo.addAttribute("titulo", "CapiBoots");
        modelo.addAttribute("titulo2", "Esta es mi aplicación CapiBoots");
        return "/administrarUsuario/registro";
    }

    @PostMapping("/registro")
    public String alta(Model modelo){
        return "exito";
    }

    //Desplegable Perfil
    @GetMapping("/suscripcion")
    public String Suscripcion (Model modelo) {
        // Buscar el registro en la BBDD
        // colocar los datos en el parámetro que pasas a la vista de Thymeleaf
        modelo.addAttribute("titulo", "Estás viendo tus datos de suscripción");
        // devuelve el archivo html con la vista
        return "/administrarUsuario/suscripcion";
    }

    @GetMapping("/ajustes")
    public String Ajustes(Model modelo) {
        modelo.addAttribute("titulo", "Ajustes");
        return "/administrarUsuario/ajustes";
    }

    @GetMapping("/logros")
    public String Logros(Model modelo) {
        modelo.addAttribute("titulo", "Logros");
        return "/administrarUsuario/logros";
    }

    //Listas de Usuarios
    @GetMapping("/lista-usuarios")
    public String listaUsus(Model modelo){
        modelo.addAttribute("listausuarios", usuSrvc.listaUsus());
        return "/listas/lista-usus";
    }
    @GetMapping("/buscarusus")
    public String buscarUsus(@PathVariable String keyword , Model modelo){
        List<Usuario> buscausu = usuSrvc.buscaUsus(keyword);
        modelo.addAttribute("buscausuarios", buscausu);
        return "/listas/lista-usus";
    }

    @GetMapping("/lista-amigos")
    public String ListaAmigos (Model modelo) {
        modelo.addAttribute("titulo", "Lista de amigos");
        return "listaAmigos";
    }

    //Crear, Guardar, Borrar y Editar

    @GetMapping("/usuario/nuevo-usuario")
    public String nuevo(Model modelo){
        modelo.addAttribute("usuario", new Usuario());
        return "/forms/nuevo-usuario";
    }

    @PostMapping("/usuario/guardar")
    public String guardar(Usuario usu){
        usuSrvc.guardar(usu);
        return "redirect:/lista-usuarios";
    }

    @GetMapping("/usuario/borrar/{id}")
    public String borrar(@PathVariable Long id){
        usuSrvc.borrar(id);
        return "redirect:/lista-usuarios";
    }

    @GetMapping("/usuario/editar/{id}")
    public String editar(@PathVariable Long id, Model modelo){
        Optional<Usuario> usuOpt = usuSrvc.buscaId(id);
        if(usuOpt.isPresent()){
            modelo.addAttribute("usuario", usuOpt.get());
        }
        else{
            // Si no existe, redirigir a una página de error o mostrar un mensaje de error
            return "error";
        }
        return "/forms/nuevo-usuario";
    }




}
