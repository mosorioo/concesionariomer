package com.taller.concesionario.dto;

// import javax.validation.Valid;
import javax.validation.constraints.Email;
 import javax.validation.constraints.Max;
// import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

// import org.hibernate.validator.constraints.Length;

import java.util.HashSet;
import java.util.Set;

public class NuevoUsuarioDto {

    //@NotBlank(message = "El campo nombre no puede estar vacio")
    @Size(min=1, max=20)
    @NotEmpty
    private String nombre;
    
    //@NotBlank
    @NotEmpty
    @Size(min=1, max=20)
    private String apellido;
    
    @NotNull
    @Max(999999999)
    private Long dni;
    
    //@NotBlank
    @NotEmpty
    @Size(min=1, max=20)
    private String nombreUsuario;
    
    @NotEmpty
    @Email
    @Size(max=20)
    private String email;
    
    //@NotBlank
    @NotEmpty
    @Size(min=6, max=6)
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
