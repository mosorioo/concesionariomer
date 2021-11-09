package com.taller.concesionario.dto;

// import javax.validation.constraints.Email;
// import javax.validation.constraints.NotBlank;
// import javax.validation.constraints.NotNull;

// import java.util.HashSet;
// import java.util.Set;

public class UsuarioDto {

    private String nombre;
    private String apellido;
    private Long dni;
    private String nombreUsuario;
    private String email;
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
