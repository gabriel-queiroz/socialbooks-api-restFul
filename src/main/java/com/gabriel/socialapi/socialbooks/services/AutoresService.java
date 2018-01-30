package com.gabriel.socialapi.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.gabriel.socialapi.socialbooks.model.Autor;
import com.gabriel.socialapi.socialbooks.repository.AutoresRepository;
import com.gabriel.socialapi.socialbooks.services.exceptions.AutorExistenteException;
import com.gabriel.socialapi.socialbooks.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {

	@Autowired	
	private AutoresRepository  autoresRepository;

	public List<Autor> listar(){

		return autoresRepository.findAll();

	}
	
	public List<Autor> listarPorLivro(Long livroId){
		
    return	autoresRepository.autoresDoLivro(livroId);
		
	}

	public Autor salvar(Autor autor) {

		if(autor.getId() != null) {

			if(autorExiste(autor.getId())) {
				
				
				throw new AutorExistenteException("autor já existe");
				
			}
		}
		
		autor.setId(null);
		return autoresRepository.save(autor);
	}
	
	
	public Autor Buscar(Long autorId) {
		
		Autor autor = autoresRepository.findOne(autorId);
		
		if(autor == null) {
			
			throw new AutorNaoEncontradoException("autor não existe");
			
		}
		
		return autor;
	}
	
	public Autor Buscar(Long livroId, Long autorId) {
		
		Autor autor = autoresRepository.buscarAutor(livroId, autorId);
		
		System.out.println(autor.getNome());
		
		autorExiste(autor.getId());
		
		return autor;
		
	}
	
	public boolean autorExiste(Long id) {

		Autor autor = autoresRepository.findOne(id);
		
		if(autor == null) {
			
			throw new AutorNaoEncontradoException();
		
		}
		return true;
		
	}
	
	
	
	public void deletar(Long autorId) {
		
		try {
		autoresRepository.delete(autorId);
		
		}catch(EmptyResultDataAccessException e) {
			
			throw new AutorNaoEncontradoException("Autor não existe");
		}
		
	}
	
	public Autor atualizar(Autor autor) {
		
		if(autor.getId() != null) {
			
			autor = autoresRepository.findOne(autor.getId());
			
			return autoresRepository.save(autor);
		
			
		}
		
		else {
			
			throw new AutorNaoEncontradoException("Autor não existe");
			
		}
		
	}

}
