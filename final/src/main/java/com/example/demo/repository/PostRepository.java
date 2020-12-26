package com.example.demo.repository;

import com.example.demo.model.Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByTituloContaining(String titulo);
    List<Post> findByPublicadoFalse();
}
