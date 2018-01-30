package com.gabriel.socialapi.socialbooks.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gabriel.socialapi.socialbooks.model.Comentario;
import com.gabriel.socialapi.socialbooks.model.exceptions.ComentarioNotFoundException;
import com.gabriel.socialapi.socialbooks.repository.ComentariosRepository;
import com.gabriel.socialapi.socialbooks.services.exceptions.ComentarioJaExisteException;

@Service
public class ComentariosService {

	@Autowired
	ComentariosRepository  comentariosRepository;


	public List<Comentario> buscarComentariosPorLivro(Long livroId){

		return comentariosRepository.findByLivroId(livroId);
	}

	public Comentario salvar(Comentario comentario) {

		if(comentario.getId() != null) {

			throw new ComentarioJaExisteException("este comentário já existe");

		}

		return	comentariosRepository.save(comentario);
	}

	public Comentario  buscar(Long livroId, Long comentarioId) {

		Comentario comentario = comentariosRepository
				.findByLivroIdAndId(livroId, comentarioId);

		comentarioEstaNulo(comentario);

		return comentario;

	}

	public void atualizar(Long livroId, Long comentarioId, Comentario comentarioAtualizado) {

		Comentario comentario = buscar(livroId, comentarioId);

		comentario.setTexto(comentarioAtualizado.getTexto());
		comentario.setData(comentarioAtualizado.getData());
		comentario.setUsuario(comentarioAtualizado.getUsuario());


		comentariosRepository.save(comentario);
	}

	private void comentarioEstaNulo(Comentario comentario) {

		if(comentario == null) {

			throw new ComentarioNotFoundException();

		}

		return;
	}
}
