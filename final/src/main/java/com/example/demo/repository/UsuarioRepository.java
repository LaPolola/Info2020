package com.example.demo.repository;

import com.example.demo.model.Usuario;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByAltaAfter(Calendar date);
    List<Usuario> findByCiudad(String ciudad);
}
