package com.example.demo.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "comentario")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 200)
    private String mensaje;

    @Temporal(TemporalType.DATE)
    @Column(updatable = false, nullable = false)
    private Calendar alta;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="autor", referencedColumnName = "id", nullable = false)
    private Usuario autor;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id", nullable = false)
    private Post post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAlta() {
        Date date = Calendar.getInstance().getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    public void setAlta(String alta) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        try {
            date = sdf.parse(alta);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            this.alta = cal;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
