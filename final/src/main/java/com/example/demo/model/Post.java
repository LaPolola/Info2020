package com.example.demo.model;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String contenido;

    @Column(updatable = false, nullable = false)
    private LocalDate alta = LocalDate.now();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="autor", referencedColumnName = "id", nullable = false)
    private Usuario autor;

    @Column(nullable = false, columnDefinition = "boolean default false")
    private Boolean publicado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getAlta() {
        return alta;
    }

    public void setAlta(LocalDate alta) {
        this.alta = alta;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void isPublicado(Boolean publicado) {
        this.publicado = publicado;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }
}
