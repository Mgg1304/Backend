package Conexiones.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import Conexiones.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    Optional<Admin> findByUsuario(String usuario);
}

