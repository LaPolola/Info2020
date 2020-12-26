package com.example.demo.repository;

import com.example.demo.model.Usuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByAltaAfter(LocalDate date);
    List<Usuario> findByCiudad(String ciudad);
}
