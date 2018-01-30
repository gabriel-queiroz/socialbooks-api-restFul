package com.gabriel.socialapi.socialbooks.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gabriel.socialapi.socialbooks.model.Autor;
import com.gabriel.socialapi.socialbooks.services.AutoresService;


@RestController
@RequestMapping("/autores")
public class AutorResource {

	@Autowired
	private AutoresService autoresService;



	@GetMapping(path = "")
	public ResponseEntity<List<Autor>> listar(){

		return ResponseEntity

				.ok()
				.body(autoresService.listar());

	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Autor> buscar(

			@PathVariable(value = "id") Long id){

		Autor autor = autoresService.Buscar(id);

		return ResponseEntity

				.ok()
				.body(autor);

	}

	@PostMapping(path = "")
	public ResponseEntity<Void> postar(

			@RequestBody Autor autor){

		autor = autoresService.salvar(autor);

		URI uri = ServletUriComponentsBuilder

				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(autor.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@DeleteMapping(path = "/{id}")
	public  ResponseEntity<Void> deletar( 

			@PathVariable(value = "id") Long id){

		autoresService.deletar(id);

		return ResponseEntity
				.noContent()
				.build();

	}

}
