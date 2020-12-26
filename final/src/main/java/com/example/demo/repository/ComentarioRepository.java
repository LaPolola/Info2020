package com.example.demo.repository;

import com.example.demo.model.Comentario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    @Query(
        value = "SELECT * FROM comentario WHERE comentario.post_id = :postId ORDER BY comentario.alta DESC LIMIT :limite",
        nativeQuery = true
    )
    List<Comentario> obtenerComentario(@Param("postId") Long postId, @Param("limite") int limite);

    @Query(
        value = "SELECT * FROM comentario WHERE comentario.post_id = :postId ORDER BY comentario.alta DESC",
        nativeQuery = true
    )
    List<Comentario> obtenerComentario(@Param("postId") Long postId);
}
