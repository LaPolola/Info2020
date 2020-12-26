package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.Post;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public ResponseEntity<?> obtenerLista() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario ususario) {
        return new ResponseEntity<>(usuarioRepository.save(ususario), HttpStatus.CREATED);
    }

    @PostMapping("/{usuarioId}/post")
    public ResponseEntity<?> crearPostUsuario(@PathVariable Long usuarioId, @RequestBody Post post) {
        Usuario usuario = usuarioRepository.getOne(usuarioId);
        usuario.addPost(post);
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    @GetMapping("/despues-alta")
    public ResponseEntity<?> obtenerUsuarioPorDespuesAlta(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<Usuario> usuarios = usuarioRepository.findByAltaAfter(date);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<?> obtenerUsuarioPorCiudad(@PathVariable("ciudad") String ciudad) {
        List<Usuario> usuarios = usuarioRepository.findByCiudad(ciudad);
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        try {
            usuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody Usuario usuario, @PathVariable("id") Long id) {

        Usuario usuarioEditado = usuarioRepository.getOne(id);
        usuarioEditado.setNombre(usuario.getNombre());
        usuarioEditado.setApellido(usuario.getApellido());
        usuarioEditado.setCiudad(usuario.getCiudad());
        usuarioEditado.setProvincia(usuario.getProvincia());
        usuarioEditado.setEmail(usuario.getEmail());
        usuarioEditado.setPais(usuario.getPais());
        usuarioEditado.setPassword(usuario.getPassword());
        return new ResponseEntity<>(usuarioRepository.save(usuarioEditado), HttpStatus.OK);
    }
}

