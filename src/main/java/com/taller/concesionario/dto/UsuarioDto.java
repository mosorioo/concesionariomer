package com.taller.concesionario.dto;

// import java.util.HashSet;
// import java.util.Set;

//import javax.validation.Valid;
import javax.validation.constraints.Email;
// import javax.validation.constraints.Max;
// import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioDto {

    //@NotBlank(message = "El campo nombre no puede estar vacio")
    @Size(min=1, max=20, message = "El nombre puede tener hasta 20 caracteres")
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    
    //@NotBlank
    @NotEmpty(message = "El apellido no puede estar vacio")
    @Size(min=1, max=20, message = "El apellido puede tener hasta 20 caracteres")
    private String apellido;
    
    @NotNull(message = "El DNI no puede estar vacio")
    private Long dni;
    
    //@NotBlank
    @NotEmpty(message = "El nombre de Usuario no puede estar vacio")
    @Size(min=1, max=20, message = "El nombre de usuario puede tener hasta 20 caracteres")
    private String nombreUsuario;
    
    @NotEmpty(message = "El campo email no puede estar vacio")
    @Email
    @Size(max=20, message = "El email puede tener hasta 20 caracteres")
    private String email;
    
    //@NotBlank
    @NotEmpty(message = "La contrasena no puede estar vacia")
    @Size(min=6, max=6, message = "La contrasena debe tener 6 caracteres") //No valida esto
    private String password;
    
    private String role;

    public UsuarioDto() {
    }

    public UsuarioDto(String nombre, String apellido, Long dni, String nombreUsuario, String email, String password,
            String role) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UsuarioDto(String nombre, String apellido, Long dni, String nombreUsuario, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Long getDni() {
        return this.dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" + " nombre='" + getNombre() + "'" + ", apellido='" + getApellido() + "'" + ", dni='" + getDni() + "'"
                + ", nombreUsuario='" + getNombreUsuario() + "'" + ", email='" + getEmail() + "'" + ", password='"
                + getPassword() + "'" + ", role='" + getRole() + "'" + "}";
    }

}

//Viejo
// public class UsuarioDto {

//     @NotBlank
//     private String nombreUsuario;
//     @NotBlank
//     private String apellido;
//     @NotBlank
//     private String nombre;
//     @NotNull
//     private Long dni;
//     @Email
//     private String email;
//     @NotBlank
//     private String password;
//     //Por defecto crea un usuario normal
//     //Si quiero un usuario Admin debo pasar este campo roles
//     private Set<String> roles = new HashSet<>();

//     public String getNombre() {
//         return nombre;
//     }

//     public void setNombre(String nombre) {
//         this.nombre = nombre;
//     }

//     public String getApellido() {
//         return apellido;
//     }

//     public void setApellido(String apellido) {
//         this.apellido = apellido;
//     }

//     public Long getDni() {
//         return dni;
//     }

//     public void setDni(Long dni) {
//         this.dni = dni;
//     }

//     public String getNombreUsuario() {
//         return nombreUsuario;
//     }

//     public void setNombreUsuario(String nombreUsuario) {
//         this.nombreUsuario = nombreUsuario;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public Set<String> getRoles() {
//         return roles;
//     }

//     public void setRoles(Set<String> roles) {
//         this.roles = roles;
//     }
// }
