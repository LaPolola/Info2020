package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Comentario;
import com.example.demo.model.Post;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ComentarioRepository comentarioRepository;

    @GetMapping
    public ResponseEntity<?> obtener() {
        List<Comentario> comentario = comentarioRepository.findAll();
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    @GetMapping("/{postId}/{limite}")
    public ResponseEntity<?> obtenerLista(@PathVariable("postId") Long postId, @PathVariable("limite") int limite) {
        List<Comentario> comentario = comentarioRepository.obtenerComentario(postId, limite);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> obtenerLista(@PathVariable("postId") Long postId) {
        List<Comentario> comentario = comentarioRepository.obtenerComentario(postId);
        return new ResponseEntity<>(comentario, HttpStatus.OK);
    }

    //POST Crear un COMENTARIO
    @PostMapping("/{postId}/{usuarioId}")
    public ResponseEntity<?> crear(@PathVariable Long postId, @PathVariable Long usuarioId, @RequestBody Comentario comentario) {
        Usuario usuario = usuarioRepository.getOne(usuarioId);
        comentario.setAutor(usuario);
        Post post = postRepository.getOne(postId);
        comentario.setPost(post);
        return new ResponseEntity<>(comentarioRepository.save(comentario), HttpStatus.CREATED);
    }

    //PUT para modificar un POST segun ID
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody Comentario comentario) {
        Comentario comentarioEdit = comentarioRepository.getOne(id);
        comentarioEdit.setMensaje(comentario.getMensaje());
        return new ResponseEntity<>(comentarioRepository.save(comentarioEdit), HttpStatus.OK);
    }

    //DELETE Borrar un Post segun el ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        Comentario comentarioDelete = comentarioRepository.getOne(id);
        comentarioRepository.delete(comentarioDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

