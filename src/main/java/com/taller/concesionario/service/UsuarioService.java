package com.taller.concesionario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;

//import javax.swing.text.html.Option;

import com.taller.concesionario.dto.NuevoUsuarioDto;
//import com.taller.concesionario.dto.ResponseDto;
import com.taller.concesionario.dto.UsuarioDto;
import com.taller.concesionario.entity.Rol;
import com.taller.concesionario.entity.Usuario;
import com.taller.concesionario.enums.RolNombre;
import com.taller.concesionario.jwt.JwtProvider;
import com.taller.concesionario.repository.UsuarioRepository;
import com.taller.concesionario.utils.Constans;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }

    public Boolean existsByUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    public boolean existsByIdUsuario(int idUsuario){
        return usuarioRepository.existsById(idUsuario);
    }

    public Boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public Boolean existsByDni(long dni){
        return usuarioRepository.existsByDni(dni);
    }

    public void save(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    //Otros mas

    public List<Usuario> listaUsuario(){
        return  usuarioRepository.findAll();
    }

    // public Optional<Usuario> getUsuario(int idUsuario){
    //     return  usuarioRepository.findById(idUsuario);
    // }

    // public void deleteUsuario(int idUsuario){
    //     usuarioRepository.deleteById(idUsuario);
    // }

        //Nuevoooo

        public UsuarioDto findUsuario(Long dni, String nombreUsuario, String email) {

            Usuario usuario = new Usuario();
    
            // Verifico que parametros llegaron dando prioridad al DNI
            if (dni != null && dni > 0) {
                usuario = usuarioRepository.findByDni(dni).get();
    
            } else if (nombreUsuario != null || !nombreUsuario.isEmpty()) {
                usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).get();
    
                //usuario = usuarioRepository.findByExample;
            } else if (email != null || !email.isEmpty()) {
                usuario = usuarioRepository.findByEmail(email).get();
            }
    
            // Extraigo el rol del objeto RolNombre
            String rolName = usuario.getRoles().stream().map(Rol::getRolNombre).collect(Collectors.toList()).get(0).name();
    
            UsuarioDto usuarioDto = new UsuarioDto(usuario.getNombre(), usuario.getApellido(), usuario.getDni(),
                    usuario.getUsuario(), usuario.getEmail(), null, rolName);
    
            return usuarioDto;
        }
    
        // public Usuario findAllUsuario(Long dni, String nombreUsuario, String email) {
    
        //     Usuario usuario = new Usuario();
    
        //     // Verifico que parametros llegaron dando prioridad al DNI
        //     if (dni != null && dni > 0) {
        //         usuario = usuarioRepository.findByDni(dni).get();
    
        //     } else if (nombreUsuario != null || !nombreUsuario.isEmpty()) {
        //         usuario = usuarioRepository.findByNombreUsuario(nombreUsuario).get();
    
        //     } else if (email != null || !email.isEmpty()) {
        //         usuario = usuarioRepository.findByEmail(email).get();
        //     }
    
        //     // Desencripto el password
        //     BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
        //     return usuario;
        // }
    
        public void crearUsuario(NuevoUsuarioDto nuevoUsuario) {
    
            // Creo un objeto de tipo Usuario con los datos enviados por parametro en el
            // objeto nuevoUsuario
            Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(), nuevoUsuario.getDni(),
                    nuevoUsuario.getNombreUsuario(), nuevoUsuario.getEmail(),
                    passwordEncoder.encode(nuevoUsuario.getPassword()));
    
            // Inicializo el objeto roles esto es para poder setear los datos en el
            // objeto(entidad)
            Set<Rol> roles = new HashSet<>();
            // POr defecto sea gregara el rol usuario
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    
            // Si dentro del objeto nuevoUsuario contiene el rol admin actualizo el que
            // setie previamente
            if (nuevoUsuario.getRoles().contains(Constans.ROLE_ADMIN)) {
                roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
            }
    
            // Seteo el rol al objeto usuario
            usuario.setRoles(roles);
    
            // Guardo los datos
            usuarioRepository.save(usuario);
        }
    
        public void editarUsuario(UsuarioDto usuarioDto) {
    
            // if (this.existsByUsuario(usuario.getNombreUsuario())) {
            // return new ResponseEntity<>(new MensajeDto("Ese nombre ya existe"),
            // HttpStatus.BAD_REQUEST);
            // }
            // if (this.existsByEmail(usuario.getEmail())) {
            // return new ResponseEntity<>(new MensajeDto("Ese email ya existe"),
            // HttpStatus.BAD_REQUEST);
            // }
    
            Usuario usuario = usuarioRepository.findByNombreUsuario(usuarioDto.getNombreUsuario()).get();
            usuario.setNombre(usuarioDto.getNombre());
            usuario.setApellido(usuarioDto.getApellido());
            usuario.setEmail(usuarioDto.getEmail());
            usuario.setDni(usuarioDto.getDni());
            //usuario.setPassword(usuarioDto.getPassword()); 
            usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
    
            // Se actualizan lo datos
            usuarioRepository.save(usuario);
        }
    
        public void borrarUsuario(String nameUser) {

            Usuario usuario = usuarioRepository.findByNombreUsuario(nameUser).orElse(new Usuario());
            //Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario).orElse(new Usuario());
            if (usuario.getUsuario() != null) {
                // Se borra el usuario
                usuarioRepository.deleteById(usuario.getIdUsuario());
            } else {
                
            }
    
        }

}
