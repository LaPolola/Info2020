package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Post;
import com.example.demo.model.Usuario;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    //GET Todos los POST
    @GetMapping // ~ /api/v1/post
    public ResponseEntity<?> obtenerLista() {
        return new ResponseEntity<>(postRepository.findAll(), HttpStatus.OK);
    }

    //POST Crear un POST
    @PostMapping("/{usuarioId}")
    public ResponseEntity<?> crear(@PathVariable Long usuarioId, @RequestBody Post post) {
        Usuario usuario = usuarioRepository.getOne(usuarioId);
        usuario.addPost(post);
        return new ResponseEntity<>(postRepository.save(post), HttpStatus.CREATED);
    }

    //PUT para modificar un POST segun ID
    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") Long id, @RequestBody Post post) {
        Post postEdit = postRepository.getOne(id);
        postEdit.setTitulo(post.getTitulo());
        postEdit.setDescripcion(post.getDescripcion());
        postEdit.setContenido(post.getContenido());
        postEdit.isPublicado(post.getPublicado());
        return new ResponseEntity<>(postRepository.save(postEdit), HttpStatus.OK);
    }

    //DELETE Borrar un Post segun el ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable("id") Long id) {
        Post postDelete = postRepository.getOne(id);
        postRepository.delete(postDelete);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/palabra-titulo/{palabra}")
    public ResponseEntity<?> obtenerPostPorPalabraTitulo(@PathVariable("palabra") String palabra) {
        List<Post> posts = postRepository.findByTituloContaining(palabra);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/sin-publicar")
    public ResponseEntity<?> obtenerPostSinPublicar() {
        List<Post> posts = postRepository.findByPublicadoFalse();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
}

