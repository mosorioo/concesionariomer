package com.taller.concesionario.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class NuevoUsuarioDto {

    //@NotBlank(message = "El campo nombre no puede estar vacio")
    @NotEmpty(message = "El campo nombre no puede estar vacio")
    private String nombre;
    //@NotBlank
    @NotEmpty(message = "El campo apellido no puede estar vacio")
    private String apellido;
    @NotNull(message = "El campo DNI no puede estar vacio")
    private Long dni;
    //@NotBlank
    @NotEmpty(message = "El campo nombre de Usuario no puede estar vacio")
    private String nombreUsuario;
    @NotEmpty(message = "El campo email no puede estar vacio")
    @Email
    private String email;
    //@NotBlank
    @NotEmpty(message = "El campo contrasena no puede estar vacio")
    private String password;
    //Por defecto crea un usuario normal
    //Si quiero un usuario Admin debo pasar este campo roles
    private Set<String> roles = new HashSet<>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
