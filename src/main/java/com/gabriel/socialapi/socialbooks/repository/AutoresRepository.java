package com.gabriel.socialapi.socialbooks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gabriel.socialapi.socialbooks.model.Autor;

public interface AutoresRepository extends JpaRepository<Autor, Long> {

	
	@Query("select l.autores from Livro l where l.id = :id")
	List<Autor> autoresDoLivro(@Param("id") Long id);
	
	
	@Query("select a from Autor a where a.id = :autorId"
			+ " and  (select l from Livro l where l.id = :livroId) "
			+ " MEMBER OF a.livros")
	Autor buscarAutor(@Param("autorId") Long autorId, @Param("livroId") Long livroId);
	
}
