package com.taller.concesionario.controller;

import com.taller.concesionario.dto.JwtDto;
import com.taller.concesionario.dto.LoginUsuario;
import com.taller.concesionario.dto.MensajeDto;
//import com.taller.concesionario.dto.UsuarioDto;
import com.taller.concesionario.dto.NuevoUsuarioDto;
import com.taller.concesionario.entity.Rol;
import com.taller.concesionario.entity.Usuario;
import com.taller.concesionario.enums.RolNombre;
import com.taller.concesionario.jwt.JwtProvider;
import com.taller.concesionario.service.RolService;
import com.taller.concesionario.service.UsuarioService;

import org.apache.commons.lang3.StringUtils;
// import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

// import org.apache.commons.lang3.StringUtils;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;

// import java.util.List;

//import antlr.StringUtils;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;



@RestController
@RequestMapping("/auth")
//@CrossOrigin //del nuevo
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;


    
    //Espera un json y lo convierte a tipo clase NuevoUsuario
    //@PostMapping("/nuevousuario")
    @PostMapping("/usuarios")
    public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody NuevoUsuarioDto nuevoUsuario,
                                          BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(new MensajeDto("Campos incompletos o email invalido"), HttpStatus.BAD_REQUEST);
        }
        if(usuarioService.existsByUsuario(nuevoUsuario.getNombreUsuario())){
            return new ResponseEntity<>(new MensajeDto("Nombre de usuario existente"), HttpStatus.CONFLICT);
        }

        if(usuarioService.existsByDni(nuevoUsuario.getDni())){
            return new ResponseEntity<>(new MensajeDto("DNI existente"), HttpStatus.CONFLICT);
        }

        if(usuarioService.existsByEmail(nuevoUsuario.getEmail())){
            return new ResponseEntity<>(new MensajeDto("Email existente"), HttpStatus.CONFLICT);
        }

        if(StringUtils.isBlank(nuevoUsuario.getNombre()))
        return new ResponseEntity<>(new MensajeDto("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(nuevoUsuario.getApellido()))
        return new ResponseEntity<>(new MensajeDto("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity<>(new MensajeDto("El nombre de usuario es obligatorio"), HttpStatus.BAD_REQUEST);

        if(StringUtils.isBlank(nuevoUsuario.getEmail()))
            return new ResponseEntity<>(new MensajeDto("El e-mail es obligatorio"), HttpStatus.BAD_REQUEST);

        if(nuevoUsuario.getDni() == null)
            return new ResponseEntity<>(new MensajeDto("El DNI es obligatorio"), HttpStatus.BAD_REQUEST);
        
        if(StringUtils.isBlank(nuevoUsuario.getPassword()))
            return new ResponseEntity<>(new MensajeDto("La contraseña es obligatoria"), HttpStatus.BAD_REQUEST);    

        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getApellido(),nuevoUsuario.getDni(), nuevoUsuario.getNombreUsuario(),
                nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));

        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);

        usuarioService.save(usuario);

        //return new ResponseEntity<>(new MensajeDto("Usuario creado"), HttpStatus.CREATED);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED); //Agregar: mostrar los datos del usuario creado
    }

        
    //Espera un json y lo convierte a tipo clase NuevoUsuario
    //url
    //Viejoooo
    // @PostMapping("/usuario")
    // public ResponseEntity<?> nuevoUsuario(@Valid @RequestBody UsuarioDto nuevoUsuario,
    //                                       BindingResult bindingResult){
    //     // if(bindingResult.hasErrors()){
    //     //     return new ResponseEntity<>(new Mensaje("Campos mal o email invalido"), HttpStatus.BAD_REQUEST);
    //     // }
    //     if(usuarioService.existsByUsuario(nuevoUsuario.getNombreUsuario())){
    //         return new ResponseEntity<>(new MensajeDto("Nombre de usuario existente"), HttpStatus.BAD_REQUEST);
    //     }
    //     if(usuarioService.existsByDni(nuevoUsuario.getDni())){
    //         return new ResponseEntity<>(new MensajeDto("DNI existente"), HttpStatus.BAD_REQUEST);
    //     }
    //     if(usuarioService.existsByEmail(nuevoUsuario.getEmail())){
    //         return new ResponseEntity<>(new MensajeDto("E-mail existente"), HttpStatus.BAD_REQUEST);
    //     }

    //     Usuario usuario = new Usuario(nuevoUsuario.getNombreUsuario(), nuevoUsuario.getApellido(), nuevoUsuario.getNombre(), nuevoUsuario.getDni(), 
    //             nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));

    //     Set<Rol> roles = new HashSet<>();
    //     roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
    //     if(nuevoUsuario.getRoles().contains("admin"))
    //         roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
    //     usuario.setRoles(roles);

    //     usuarioService.save(usuario);

    //     //return new ResponseEntity<>(new Mensaje("Usuario creado"), HttpStatus.CREATED); //Agregar: mostrar los datos del usuario creado
    //     return new ResponseEntity<>(usuario, HttpStatus.CREATED); //Agregar: mostrar los datos del usuario creado
      
    // }

    //@GetMapping("/login")//
  //  @CrossOrigin
    // @PostMapping("/login")
    // public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
    //     if (bindingResult.hasErrors())
    //         return new ResponseEntity(new MensajeDto("Campos mal"), HttpStatus.BAD_REQUEST);
    //     Authentication authentication =
    //             authenticationManager.authenticate(
    //                     new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
    //                             loginUsuario.getPassword()));
    //     SecurityContextHolder.getContext().setAuthentication(authentication);
    //     String jwt = jwtProvider.generateToken(authentication);
    //     UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    //     JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
    //     //JwtDto jwtDto = new JwtDto(jwt, userDetails.getIdUsuario(), userDetails.getAuthorities());
    //     return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    // }

    //@PostMapping("/login")
    @PostMapping("/session")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new MensajeDto("Campos vacios o incompletos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(),
                                loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }


    //Viejooooo
    // // Actualizar usuario x id
    // @PutMapping("/actualizarUsuario/{idUsuario}")
    // public ResponseEntity<?> actualizarUsuario(@PathVariable("idUsuario") int idUsuario, @RequestBody UsuarioDto usuarioDto){

    //     if (!usuarioService.existsByIdUsuario(idUsuario))
    //     return new ResponseEntity(new MensajeDto("No existe el usuario"), HttpStatus.NOT_FOUND);

    //     if (usuarioService.existsByUsuario(usuarioDto.getNombreUsuario())
    //             && usuarioService.getByUsuario(usuarioDto.getNombreUsuario()).get().getIdUsuario() != idUsuario)
    //         return new ResponseEntity(new MensajeDto("El nombre del usuario ya existe"), HttpStatus.NOT_FOUND);

    //     if(StringUtils.isBlank(usuarioDto.getNombreUsuario()))
    //         return new ResponseEntity(new MensajeDto("El nombre de usuario es obligatorio"), HttpStatus.BAD_REQUEST);

    //     if(StringUtils.isBlank(usuarioDto.getNombreUsuario()))
    //         return new ResponseEntity(new MensajeDto("El nombre de usuario es obligatorio"), HttpStatus.BAD_REQUEST);

    //     if(usuarioDto.getDni() == null)
    //         return new ResponseEntity(new MensajeDto("El DNI es obligatorio"), HttpStatus.BAD_REQUEST);

    //     Usuario usuario = usuarioService.getUsuario(idUsuario).get();
    //     usuario.setUsuario(usuarioDto.getNombreUsuario());
    //     usuario.setApellido(usuarioDto.getApellido());
    //     usuario.setNombre(usuarioDto.getNombre());
    //     usuario.setDni(usuarioDto.getDni());
    //     usuario.setEmail(usuarioDto.getEmail());
    //     usuario.setPassword(usuarioDto.getPassword());
    //     usuario.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));
        
    //     usuarioService.save(usuario);
    //     //return new ResponseEntity(new Mensaje("Usuario actualizado"), HttpStatus.OK);
    //     return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    // }

    // //EL path modifica solo un dato

    // //Eliminar usuario x id FUNCIONA
    // @DeleteMapping("/borrarUsuario/{idUsuario}")
    // public ResponseEntity<?> borrarUsuario(@PathVariable("idUsuario") int idUsuario){
    //     if (!usuarioService.existsByIdUsuario(idUsuario))
    //         return new ResponseEntity(new MensajeDto("No existe el Usuario"), HttpStatus.NOT_FOUND);
    //     usuarioService.deleteUsuario(idUsuario);
    //     return new ResponseEntity(new MensajeDto("Usuario eliminado"), HttpStatus.OK);
    // }
    
    // //Obtener lista de usuarios FUNCIONA
    // @GetMapping("/listaUsuario")
    // public ResponseEntity<List<Usuario>> listaUsuarios(){

    //     List<Usuario> usuarios = usuarioService.listaUsuario();
    //     return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
    // }

    // @GetMapping("/consultarUsuario/{idUsuario}")
    // public ResponseEntity<Usuario> usuarioById(@PathVariable("idUsuario") int idUsuario){

    //     if (!usuarioService.existsByIdUsuario(idUsuario))
    //         return new ResponseEntity(new MensajeDto("No existe el usuario"), HttpStatus.NOT_FOUND);

    //     Usuario usuario = usuarioService.getUsuario(idUsuario).get();
    //     //return new ResponseEntity(usuario, HttpStatus.OK);
    //     return new ResponseEntity<>(usuario, HttpStatus.OK);
    // }

    // @GetMapping("/consultaUsuarioNombre/{nombreUsuario}")
    // public ResponseEntity<Usuario> usuarioByNombreUsuario(@PathVariable("nombreUsuario") String nombreUsuario){

    //     if (!usuarioService.existsByUsuario(nombreUsuario))
    //         return new ResponseEntity(new MensajeDto("No existe el usuario"), HttpStatus.NOT_FOUND);

    //     Usuario usuario = usuarioService.getByUsuario(nombreUsuario).get();
    //     return new ResponseEntity<>(usuario, HttpStatus.OK);
    // }

}
