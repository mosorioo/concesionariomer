package com.taller.concesionario.controller;

// import java.util.HashSet;
// import java.util.Optional;
// import java.util.Set;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.Valid;

import com.taller.concesionario.dto.MensajeDto;
import com.taller.concesionario.dto.NuevoUsuarioDto;
import com.taller.concesionario.dto.UsuarioDto;
//import com.taller.concesionario.entity.Rol;
import com.taller.concesionario.entity.Usuario;
//import com.taller.concesionario.enums.RolNombre;
import com.taller.concesionario.jwt.JwtProvider;
import com.taller.concesionario.service.RolService;
import com.taller.concesionario.service.UsuarioService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import org.apache.commons.lang3.StringUtils;

@RestController
//@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    private static final Logger logger = LogManager.getLogger(UsuarioController.class);

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

    /**
     * Metodo que obtiene los datos basicos de usuario
     * @param dni
     * @param nombreUsuario
     * @param email
     * @return
     */
    // @GetMapping("/buscar")
    // public @ResponseBody UsuarioDto buscarUsuario(@RequestParam(value = "dni", defaultValue = "0") Long dni,
    //         @RequestParam(value = "nombre-usuario") String nombreUsuario, 
    //         @RequestParam(value = "email") String email) {

    //     try {

    //         return usuarioService.findUsuario(dni, nombreUsuario, email);

    //     } catch (Exception e) {
    //         logger.error("qualitySensitive" + e.getMessage() + e.getStackTrace());
    //         return null;
    //     }
    // }


    //@GetMapping("/buscar")
    @GetMapping("/usuarios")
    public @ResponseBody UsuarioDto buscarUsuario(@RequestParam(value = "dni", required=false) Long dni,
            @RequestParam(value = "nombre-usuario" , required=false) String nombreUsuario, 
            @RequestParam(value = "email",required=false) String email) {

        try {

            return usuarioService.findUsuario(dni, nombreUsuario, email);

        } catch (Exception e) {
            logger.error("qualitySensitive" + e.getMessage() + e.getStackTrace());
            return null;
        }
    }

    /**
     * Metodo que obtiene todos los datos del usuario incluyendo el password 
     * @param dni
     * @param nombreUsuario
     * @param email
     * @return
     */

    //@GetMapping("/buscar-todo")
    // @GetMapping("/usuarios")
    // public @ResponseBody Usuario buscarTodoUsuario(@RequestParam(value = "dni", defaultValue = "0") Long dni,
    //         @RequestParam(value = "nombre-usuario") String nombreUsuario, 
    //         @RequestParam(value = "email") String email) {

    //     try {

    //         return usuarioService.findAllUsuario(dni, nombreUsuario, email);

    //     } catch (Exception e) {
    //         logger.error("qualitySensitive" + e.getMessage() + e.getStackTrace());
    //         return null;
    //     }
    // }

    // Espera un json y lo convierte a tipo clase NuevoUsuario
    // @PostMapping("/crear")
    // public ResponseEntity<?> crearUsuario(@Valid @RequestBody NuevoUsuarioDto nuevoUsuario, BindingResult bindingResult) {

    //     try {
    //         if (bindingResult.hasErrors()) {
    //             return new ResponseEntity<>(new MensajeDto("Campos mal o email invalido"), HttpStatus.BAD_REQUEST);
    //         }
    //         if (usuarioService.existsByUsuario(nuevoUsuario.getNombreUsuario())) {
    //             return new ResponseEntity<>(new MensajeDto("Ese nombre ya existe"), HttpStatus.BAD_REQUEST);
    //         }
    //         if (usuarioService.existsByEmail(nuevoUsuario.getEmail())) {
    //             return new ResponseEntity<>(new MensajeDto("Ese email ya existe"), HttpStatus.BAD_REQUEST);
    //         }

    //         usuarioService.crearUsuario(nuevoUsuario);
    //         return new ResponseEntity<>(new MensajeDto("Usuario creado"), HttpStatus.CREATED);

    //     } catch (Exception e) {
    //         logger.error("qualitySensitive" + e.getMessage() + e.getStackTrace());
    //         return new ResponseEntity<>(new MensajeDto("Error al crear usuario"), HttpStatus.BAD_REQUEST);
    //     }   
    // }

    //@PutMapping("/editar")
    @PutMapping("/usuarios")
    public ResponseEntity<?> editarUsuario(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult bindingResult) {

        //Generico
        try {
            
            // if(StringUtils.isBlank(usuarioDto.getNombre()))
            // return new ResponseEntity<>(new MensajeDto("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
    
            // if(StringUtils.isBlank(usuarioDto.getApellido()))
            // return new ResponseEntity<>(new MensajeDto("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
    
            // if(StringUtils.isBlank(usuarioDto.getNombreUsuario()))
            //     return new ResponseEntity<>(new MensajeDto("El nombre de usuario es obligatorio"), HttpStatus.BAD_REQUEST);
    
            // if(StringUtils.isBlank(usuarioDto.getEmail()))
            //     return new ResponseEntity<>(new MensajeDto("El e-mail es obligatorio"), HttpStatus.BAD_REQUEST);
    
            // if(usuarioDto.getDni() == null)
            //     return new ResponseEntity<>(new MensajeDto("El DNI es obligatorio"), HttpStatus.BAD_REQUEST); 
    
            // if(StringUtils.isBlank(usuarioDto.getPassword()))
            //     return new ResponseEntity<>(new MensajeDto("La contrase??a es obligatoria"), HttpStatus.BAD_REQUEST);    
            
            // if(usuarioService.existsByUsuario(usuarioDto.getNombreUsuario())){
            //     return new ResponseEntity<>(new MensajeDto("Nombre de usuario existente"), HttpStatus.CONFLICT);
            // }

            if (bindingResult.hasErrors()) {
                return new ResponseEntity<>(new MensajeDto("Campos incorrectos o incompletos. Nombre, Apellido, Nombre de Usuario, Email (Cumplir Formato Email): MAX 20 caracteres, DNI MAX 999999999, Contrasena = 6 caracteres"), HttpStatus.BAD_REQUEST);
            }
    
            usuarioService.editarUsuario(usuarioDto);
            return new ResponseEntity<>(usuarioDto, HttpStatus.OK); //Agregar: mostrar los datos del usuario creado

        } catch (Exception e) {
            logger.error("qualitySensitive" + e.getMessage() + e.getStackTrace());
            return new ResponseEntity<>(new MensajeDto("Error al editar usuario"), HttpStatus.BAD_REQUEST);
        }
    }

    //@DeleteMapping("/borrar")
    //@DeleteMapping("/usuarios")
    @DeleteMapping("/me")
    public ResponseEntity<?> borrarUsuario(UsernamePasswordAuthenticationToken user) {

        try {
            usuarioService.borrarUsuario(user.getName());
            return new ResponseEntity<>(new MensajeDto("Usuario borrado"), HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            logger.error("qualitySensitive" + e.getMessage() + e.getStackTrace());
            return new ResponseEntity<>(new MensajeDto("Error al borrar el usuario"), HttpStatus.BAD_REQUEST);
        }
    }   
}
