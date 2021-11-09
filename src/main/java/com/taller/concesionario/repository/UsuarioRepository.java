package com.taller.concesionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.taller.concesionario.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    // Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    // boolean existsByNombreUsuario (String nombreUsuario);
    // boolean existsByEmail (String email);
    // boolean existsById(int idUsuario); //lo agregué
    boolean existsByDni(long dni);
    // boolean existsByApellido (String apellido);
    // boolean existsByNombre (String nombre);

    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByDni(Long dni);
    Optional<Usuario> findByEmail(String email);
    boolean existsByNombreUsuario (String nombreUsuario);
    boolean existsByEmail (String email);
}
