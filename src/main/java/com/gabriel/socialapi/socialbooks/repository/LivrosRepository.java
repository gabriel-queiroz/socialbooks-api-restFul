package com.gabriel.socialapi.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.gabriel.socialapi.socialbooks.model.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long> {

}
