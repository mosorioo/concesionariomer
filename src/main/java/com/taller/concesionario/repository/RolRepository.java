package com.taller.concesionario.repository;

import com.taller.concesionario.entity.Rol;
import com.taller.concesionario.enums.RolNombre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
