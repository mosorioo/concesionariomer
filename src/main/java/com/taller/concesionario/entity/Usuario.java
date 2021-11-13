package com.taller.concesionario.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
 import javax.validation.constraints.Max;
// import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
/**
 * Clase para la base de datos
 */
@Entity
public class Usuario {
    //Id de la tabla
    @Id
    //Id Auto Increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    //Decorador para indicar que no puede ser null el campo
    @NotNull
    @Column(unique = true)
    private String nombreUsuario;

    @NotNull
    private String apellido;

    @NotNull
    @NotBlank(message = "Name is mandatory")
    private String nombre;

    @NotNull
    @Column(unique = true, length = 10)
    private Long dni;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    //Relación many to many
    //Un usuario puede tener MUCHOS roles y un rol puede PERTENECER a varios usuarios
    //Tabla intermedia que tiene dos campos que va a tener idUsuario y idRol
    @ManyToMany
    // join columns hace referencia a la columna que hace referencia hacia esta
    // Es decir la tabla usuario_rol va a tener un campo que se llama id_usuario
    // inverseJoinColumns = el inverso, hace referencia a rol
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "id_usuario"),
    inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {
    }

    //Constuctor sin Id ni Roles
    //viejooo
    public Usuario(@NotNull String nombreUsuario, @NotNull String apellido, @NotNull String nombre,  @NotNull Long dni, @NotNull String email, @NotNull String password) {
        this.nombreUsuario = nombreUsuario;
        this.apellido = apellido;
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.password = password;
    }

    // Constuctor sin Id ni Roles
    public Usuario(@NotNull String nombre, @NotNull String apellido, @NotNull Long dni, @NotNull String nombreUsuario, @NotNull String email, @NotNull String password) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.dni = dni;
    this.nombreUsuario = nombreUsuario;
    this.email = email;
    this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

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

    public String getUsuario() {
        return nombreUsuario;
    }

    public void setUsuario(String usuario) {
        this.nombreUsuario = usuario;
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

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
}
