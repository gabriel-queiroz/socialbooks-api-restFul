package com.gabriel.socialapi.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gabriel.socialapi.socialbooks.model.Autor;
import com.gabriel.socialapi.socialbooks.model.Comentario;
import com.gabriel.socialapi.socialbooks.model.Livro;
import com.gabriel.socialapi.socialbooks.repository.LivrosRepository;
import com.gabriel.socialapi.socialbooks.services.exceptions.AutorExistenteException;
import com.gabriel.socialapi.socialbooks.services.exceptions.LivroNotFoundException;

@Service
public class LivrosServices {

	@Autowired
	private LivrosRepository livroRepository;

	@Autowired
	private AutoresService autoresService;

	@Autowired
	private ComentariosService comentariosService;

	
	public List<Livro> listar(){

		return livroRepository.findAll();

	}

	public Livro buscar(Long id) {

		Livro livro = livroRepository.findOne(id);

		if(livro == null) {

			throw new LivroNotFoundException("livro não encontrado");
		}
		return livro;
	}

	public Livro salvar(Livro  livro) {

		 System.out.println(livro);
		
		 livro.setId(null);
		
		 livro = livroRepository.save(livro);
		 
		 atualizar(livro);
		 
		 return livro;
	}
	
	public void deletar(Long id) {

		try {

			livroRepository.delete(id);

		}catch (EmptyResultDataAccessException e) {

			throw new LivroNotFoundException("livro não pôde ser encontrado"); 

		}
	}

	public void atualizar(Livro livro) {


		verificarExistencia(livro);
		livroRepository.save(livro);

	}

	public 	Comentario salvarComentario(Long livroId, 
										  Comentario comentario) {

		Livro livro = buscar(livroId); 
		livro.adicionarComentario(comentario);
		return comentariosService.salvar(comentario);
	}

	public List<Comentario> listarComentario(Long livroId){

		return comentariosService
				.buscarComentariosPorLivro(livroId);
	}
	
	public void atualizarComentario(Long livroId, 
									  Long comentarioId, 
									  Comentario comentarioAtualizado) {
		
		
		comentariosService.atualizar(livroId, 
									comentarioId, 
									comentarioAtualizado);
		
	}

	public Comentario buscarComentario(Long livroId, Long comentarioId) {

		 return comentariosService.buscar(livroId, comentarioId);
	}

	private void verificarExistencia(Livro livro) {

		buscar(livro.getId());

	}

	public Autor adicionarAutor(Long livroId, Autor autor) {

		try {
			
			autor = autoresService.salvar(autor);
			
			Livro livro = buscar(livroId);
			
			livro.adicionarAutor(autor);
			
			atualizar(livro);
			
		    return autoresService.atualizar(autor);
		
		
		}catch(AutorExistenteException e) {
			
			Livro livro = buscar(livroId);
			
			contemAutor(livro, autor);
			
			livro.adicionarAutor(autor);
			
			atualizar(livro);
			
			return livro.buscarAutor(autor.getId());
			
		}
		

	}
	
	public List<Autor> listarAutores(Long livroId){
		
		return autoresService.listarPorLivro(livroId);
	}
	
	public Autor buscarAutor(Long livroId, Long autorId) {
		
		
		return autoresService.Buscar(livroId, autorId);
		
	
	}

	private void contemAutor(Livro livro, Autor autor) {
		
		if(livro.getAutores().contains(autor)) {
			
			throw new AutorExistenteException("já contém esse autor no livro");
			
		}
		return;
		
	}
	
	
	
}
