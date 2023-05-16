/*
package com.example.CapiBoots.serviciosTest.utilTest;



import com.example.CapiBoots.modelos.Rol;
import com.example.CapiBoots.modelos.Usuario;
import com.example.CapiBoots.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;



@Service
public class UserDetailsServiceImplTest implements UserDetailsService {

    @Autowired
    private final UsuarioRepositorio userRepository; // Inyección de dependencia del UserRepository

    public UserDetailsServiceImplTest(UsuarioRepositorio userRepository) {
        this.userRepository = userRepository;
    }



//    @Override
//    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
//        // Buscar el usuario por su email utilizando el UserRepository
//        Usuario user = userRepository.findByNombreUsuario(nombre);
//
//        // Si el usuario es encontrado, crear una instancia de UserDetails utilizando los datos del usuario
//        if (user != null) {
//            return new org.springframework.security.core.userdetails.User(user.getNombreUsuario(),
//                    user.getClave(),
//                    mapRolesToAuthorities(user.getRoles())); // mapRolesToAuthorities es una función auxiliar que se define más abajo
//        }else{
//            // Si el usuario no es encontrado, lanzar una excepción UsernameNotFoundException
//            throw new UsernameNotFoundException("Invalid username or password.");
//        }
//    }

    @Override
    public CustomUserDetailsTest loadUserByUsername(String nombre) throws UsernameNotFoundException {
    // Buscar el usuario por su email utilizando el UserRepository
        Usuario user = userRepository.findByNombreUsuario(nombre);

        // Si el usuario es encontrado, crear una instancia de UserDetails utilizando los datos del usuario
        if (user != null) {
            CustomUserDetailsTest customUserDetails = new CustomUserDetailsTest(
                    user.getNombreUsuario(),
                    user.getClave(),
                    user.getCorreo(),
                    user.getGenero(),
                    user.getEdad(),
                    user.getPais(),
                    mapRolesToAuthorities(user.getRoles())
            );
            return customUserDetails;

        }else{
            // Si el usuario no es encontrado, lanzar una excepción UsernameNotFoundException
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    */
/**
     * Esta función auxiliar se utiliza para convertir la lista de roles del usuario en una colección de
     * autoridades que pueden ser utilizadas por Spring Security.
     *
     * @param roles Lista de roles del usuario.
     * @return Collection< ? extends GrantedAuthority> Colección de autoridades.
     *//*

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(List<Rol> roles) {
        // Utilizar streams de Java para mapear cada rol a una instancia de SimpleGrantedAuthority
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .toList(); // Convertir el stream en una lista
    }
}

*/
