package com.gabriel.socialapi.socialbooks.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.socialapi.socialbooks.model.Autor;
import com.gabriel.socialapi.socialbooks.model.Comentario;
import com.gabriel.socialapi.socialbooks.model.Livro;
import com.gabriel.socialapi.socialbooks.repository.ComentariosRepository;
import com.gabriel.socialapi.socialbooks.repository.LivrosRepository;
import com.gabriel.socialapi.socialbooks.services.LivrosServices;




@RestController
@RequestMapping("/livros")
public class LivroResource {

	@Autowired
	LivrosRepository livroRepository;

	@Autowired
	LivrosServices livrosService;

	@Autowired
	ComentariosRepository comentariosRepository;


	@GetMapping(path ="")
	public ResponseEntity<List<Livro>> listar() {


		return ResponseEntity
				.status(HttpStatus.OK)
				.body(livrosService.listar());

	}

	@GetMapping(path ="/{id}"	)
	public ResponseEntity<Livro> buscar(

			@PathVariable(value = "id") Long livroId){

		Livro livro = livrosService.buscar(livroId);

		return ResponseEntity
				.ok()
				.body(livro);
	}

	@GetMapping(path = "/{id}/comentarios")
	public ResponseEntity<List<Comentario>> buscarComentario(

			@PathVariable(value = "id") Long livroId){

		List<Comentario> comentarios = comentariosRepository
				.findByLivroId(livroId);

		return ResponseEntity
				.ok()
				.body(comentarios);
	}

	@GetMapping(path = "/{id}/autores")
	public ResponseEntity<List<Autor>> listarAutoresLivro(

			@PathVariable(value = "id")Long livroId){

		List<Autor> listarAutores = livrosService
				.listarAutores(livroId);


		return ResponseEntity
				.ok()
				.body(listarAutores);
	}

	@GetMapping(path = "/{id}/comentarios/{idComentario}")
	public ResponseEntity<Comentario> listarComentarios(

			@PathVariable(value = "id") Long livroId,
			@PathVariable(value = "idComentario") Long comentarioId){

		Comentario comentario = livrosService
				.buscarComentario(livroId, comentarioId);

		return ResponseEntity
				.ok()
				.body(comentario);

	}	

	@GetMapping(path = "/{id}/autores/{idAutor}")
	public ResponseEntity<Autor> buscarAutor( 

			@PathVariable(value= "id") Long livroId,
			@PathVariable(value = "idAutor") Long autorId){

		Autor autor = livrosService.buscarAutor(livroId, autorId);



		return ResponseEntity
				.status(HttpStatus.OK)
				.body(autor);

	}

	@PostMapping(path = "/livros")
	public ResponseEntity<Void> postarLivro(

			@RequestBody @Valid Livro livro ) {

		livro = livrosService.salvar(livro);

		URI uri = ServletUriComponentsBuilder

				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(livro.getId()).toUri();

		return ResponseEntity.created(uri).build();
	} 

	@PostMapping(path = "/{id}/comentarios")
	public ResponseEntity<Void> postarComentario(

			@PathVariable(value = "id") Long livroId,
			@RequestBody @Valid  Comentario comentario) {

		comentario = livrosService
				.salvarComentario(livroId, comentario);

		URI uri = ServletUriComponentsBuilder

				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(comentario.getId()).toUri();

		return ResponseEntity.created(uri).build();


	}

	@PostMapping(path = "/{id}/autores")
	public ResponseEntity<Autor> adicionarAutor(

			@PathVariable(value = "id") Long id,
			@RequestBody @Valid Autor autor){

		autor = livrosService.adicionarAutor(id, autor);

		URI uri = ServletUriComponentsBuilder

				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(autor.getId()).toUri();

		return ResponseEntity.created(uri).build();

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Void> atualizarLivro( 

			@PathVariable(value = "id") Long livroId,
			@RequestBody @Valid Livro livro){

		livro.setId(livroId);

		livrosService.atualizar(livro);

		return ResponseEntity.ok().build();

	}

	@PutMapping(path = "/{id}/comentarios/{idComentario}")
	public ResponseEntity<Void> atualizarComentario(

			@PathVariable(value = "id") Long livroId,
			@PathVariable(value = "idComentario") Long comentarioId,
			@RequestBody @Valid Comentario comentario ){



		livrosService.atualizarComentario(livroId, comentarioId, comentario);



		return ResponseEntity.ok().build();

	}


	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Void> deletarLivro(

			@PathVariable(value = "id") Long LivroId){

		livrosService.deletar(LivroId);
		return ResponseEntity.noContent().build();

	}





}
