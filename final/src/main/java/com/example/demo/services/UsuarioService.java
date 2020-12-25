package com.example.demo.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.demo.models.UsuarioModel;
import com.example.demo.repositories.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioModel> obtenerUsuarios() {
        return (List<UsuarioModel>) usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<UsuarioModel> obtenerPorCiudad(String ciudad) {
        return usuarioRepository.findByCiudad(ciudad);
    }

    public List<UsuarioModel> obtenerPorDespuesAlta(String alta) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        Calendar cal = null;
        try {
            date = sdf.parse(alta);
            cal = Calendar.getInstance();
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return usuarioRepository.findByAltaAfter(cal);
    }

    public boolean eliminarUsuario(Long id) {
        try {
            usuarioRepository.deleteById(id);
            return true;
        } catch(Exception err) {
            return false;
        }
    }
}
