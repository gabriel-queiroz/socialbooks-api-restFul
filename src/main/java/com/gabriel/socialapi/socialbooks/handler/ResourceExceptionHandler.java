package com.gabriel.socialapi.socialbooks.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.gabriel.socialapi.socialbooks.model.DetalhesErro;
import com.gabriel.socialapi.socialbooks.model.exceptions.ComentarioNotFoundException;
import com.gabriel.socialapi.socialbooks.services.exceptions.AutorExistenteException;
import com.gabriel.socialapi.socialbooks.services.exceptions.AutorNaoEncontradoException;
import com.gabriel.socialapi.socialbooks.services.exceptions.ComentarioJaExisteException;
import com.gabriel.socialapi.socialbooks.services.exceptions.LivroNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(LivroNotFoundException.class)
	public ResponseEntity<DetalhesErro> handlerLivroNaoEncontradoException(
									
									LivroNotFoundException e, 
									HttpServletRequest req){

		DetalhesErro erro = new DetalhesErro("o Livro não ser encontrado",
				   							   404l,  
				   							   System.currentTimeMillis(), 
				   							   "http://erros.socialbooks.com/404");

		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(erro);


	}

	@ExceptionHandler(ComentarioNotFoundException.class)
	public ResponseEntity<DetalhesErro> handlerComentarioNaoEncontradoException(

									ComentarioNotFoundException e,
									HttpServletRequest req){
		
		DetalhesErro erro = new DetalhesErro("o Comentário não pôde ser  encontrado",
											   404l,  
											   System.currentTimeMillis(), 
											   "http://erros.socialbooks.com/404");
		
		
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(erro);
		

		
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<DetalhesErro> handlerBeanValidationException(
											MethodArgumentNotValidException e,
											HttpServletRequest req){
		
		DetalhesErro erro = new DetalhesErro("erro ao validar o bean",
											   404l,  
											   System.currentTimeMillis(), 
				   							   "http://erros.socialbooks.com/404");
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(erro);
		
	}
	
	
	@ExceptionHandler(AutorExistenteException.class)
	public ResponseEntity<DetalhesErro> autorExistenteException(

									AutorExistenteException e,
									HttpServletRequest req){
		
		DetalhesErro erro = new DetalhesErro(e.getMessage(),
											  409l,  
											  System.currentTimeMillis(), 
				   							  "http://erros.socialbooks.com/409");
		
		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(erro);
		
	}
	
	
	@ExceptionHandler(AutorNaoEncontradoException.class)
	public ResponseEntity<DetalhesErro> autorNaoEncontradoException(

									AutorNaoEncontradoException e,
									HttpServletRequest req){
		
		DetalhesErro erro = new DetalhesErro("Não existe autor com esse id",
				  							  404l,  
				  							  System.currentTimeMillis(), 
					  						  "http://erros.socialbooks.com/404");


		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(erro);
		

		
	}
	
	
	@ExceptionHandler(ComentarioJaExisteException.class)
	public ResponseEntity<DetalhesErro> comentarioJaExisteException(

									ComentarioJaExisteException e,
									HttpServletRequest req){
		
		DetalhesErro erro = new DetalhesErro(e.getMessage(),
				  							  409l,  
				  							  System.currentTimeMillis(), 
					  						  "http://erros.socialbooks.com/404");


		return ResponseEntity
				.status(HttpStatus.CONFLICT)
				.body(erro);
		

		
	}


}
