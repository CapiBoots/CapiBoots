//package com.example.CapiBoots.serviciosTest;
//
//import com.example.CapiBoots.dto.UsuarioDto;
//import com.example.CapiBoots.modelos.Rol;
//import com.example.CapiBoots.modelos.Usuario;
//import com.example.CapiBoots.repositorios.RolRepositorio;
//import com.example.CapiBoots.repositorios.UsuarioRepositorio;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//public class UsuarioSrvcImplsTest implements ifxUsuarioSrvcTest {
//
//    @Autowired
//    private UsuarioRepositorio usurepo;
//
//
//    @Override
//    public Optional<Usuario> buscaId(Long id) {
//        return usurepo.findById(id);
//    }
//
//    @Override
//    public Usuario buscaPorNombre(String nombre_usuario) {
//        return usurepo.findByNombreUsuario(nombre_usuario);
//    }
//
//    public Usuario buscaPorCorreo(String correo) {
//        return usurepo.findByCorreo(correo);
//    }
//
//
//    //Buscador por palabra clave
//    @Override
//    public List<Usuario> buscaUsus(String keyword) {
//        if (keyword != null) {
//            return usurepo.buscarTodos(keyword);
//        }
//        return usurepo.findAll();
//    }
//
//    //Listar
//    @Override
//    public List<Usuario> listaUsus() {
//        return usurepo.findAll();
//    }
//
//
//
//
//    //Guardar y Borrar porque Crear/Editar se definen en el controlador.
//
//    public Usuario guardarUs(Usuario user){
//        user.setClave(passwordEncoder.encode(user.getClave()));
//        Rol rol = roleRepository.findByName(defaultUserRole);
//
//        if(rol == null){
//            rol = addRoleIfNotExists();
//        }
//        user.setRoles(List.of(rol));
//        return usurepo.save(user);
//    }
//
//
//    public void guardar(UsuarioDto userDto) {
//
//        //Guarda los datos del Dto en la entidad Usuario, codificando la clave y asignando un rol.
//        Usuario user = new Usuario();
//        user.setNombreUsuario(userDto.getNombre_usuario());
//        user.setEdad(userDto.getEdad());
//        user.setGenero(userDto.getGenero());
//        user.setCorreo(userDto.getCorreo());
//        user.setPais(userDto.getPais());
//        // encrypt the password using spring security
//        user.setClave(passwordEncoder.encode(userDto.getClave()));
//        Rol rol = roleRepository.findByName(defaultUserRole);
//
//        if(rol == null){
//            rol = addRoleIfNotExists();
//        }
//        user.setRoles(List.of(rol));
//        usurepo.save(user);
//    }
//
//    public void borrar(Long id) {
//        usurepo.deleteById(id);
//    }
//
//    //Seguridad----------------------------------------------------------------------------------------------------
//
//    private final UsuarioRepositorio userRepository;
//    private final RolRepositorio roleRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Value("${rol.usuario.defecto}") // Se cambia en el application.properties
//    private String defaultUserRole;
//
//
//    //Creamos un constructor de UsuarioSrvcImpls
//    public UsuarioSrvcImplsTest(UsuarioRepositorio userRepository,
//                                RolRepositorio roleRepository,
//                                PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    public List<UsuarioDto> findAllUsers() {
//        List<Usuario> usuarios = userRepository.findAll();
//        return usuarios.stream()
//                .map((usuario) -> mapToUserDto(usuario))
//                .collect(Collectors.toList());
//    }
//
//    private UsuarioDto mapToUserDto(Usuario usuario){
//        UsuarioDto usuarioDto = new UsuarioDto();
//        usuarioDto.setNombre_usuario(usuario.getNombreUsuario());
//        usuarioDto.setCorreo(usuario.getCorreo());
//        return usuarioDto;
//    }
//
//    //Añade un rol al nuevo usuario si no lo tiene ya
//    private Rol addRoleIfNotExists(){
//        Rol role = new Rol();
//        role.setName(defaultUserRole);
//        return roleRepository.save(role);
//    }
//
//    //Restaurar Contraseña olvidada
//    public void updateResetPasswordToken(String token, String nombre_usuario) throws UsernameNotFoundException {
//
//        //Encuentra al usuario por su nombre de usuario (único)
//        Usuario user = userRepository.findByNombreUsuario(nombre_usuario);
//        //Si el usuario existe, aplica un token al usuario
//        //para cambiar la clave más tarde y guarda de nuevo al
//        //usuario con el token listo para ser usado
//        if (user != null) {
//            user.setTokenRestaurarContra(token);
//            userRepository.save(user);
//        } else {
//            throw new UsernameNotFoundException("Could not find any user with the email " + nombre_usuario);
//        }
//    }
//
//
//    public Usuario getByRestaurarContra(String token) {
//        return userRepository.findByTokenRestaurarContra(token);
//    }
//
//    public void updatePassword(Usuario user, String nuevaClave) {
//
//        //Crea un codificador
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        //Crea una clave codificada usando el codificador sobre la clave que hemos introducido (nuevaClave)
//        String claveCodificada = passwordEncoder.encode(nuevaClave);
//        //Aplica al usuario esa nueva clave ya codificada
//        user.setClave(claveCodificada);
//
//        //Desactivamos el token de restauración de clave y guardamos el usuario
//        user.setTokenRestaurarContra(null);
//        userRepository.save(user);
//    }
//
//}
