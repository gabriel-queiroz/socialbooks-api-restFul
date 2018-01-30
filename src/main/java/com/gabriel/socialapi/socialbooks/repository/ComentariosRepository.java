package com.gabriel.socialapi.socialbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gabriel.socialapi.socialbooks.model.Comentario;

public interface ComentariosRepository extends JpaRepository<Comentario, Long> {


	List<Comentario> findByLivroId(Long livroId);

	Comentario findByLivroIdAndId(Long livroId,Long id);
}
