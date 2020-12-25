package com.example.demo.repositories;

import java.util.Calendar;
import java.util.List;

import com.example.demo.models.UsuarioModel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioModel, Long> {
    public abstract List<UsuarioModel> findByCiudad(String ciudad);
	public abstract List<UsuarioModel> findByAltaAfter(Calendar date);
}
