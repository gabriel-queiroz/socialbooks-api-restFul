package com.gabriel.socialapi.socialbooks.services.exceptions;

public class LivroNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LivroNotFoundException(String mensagem) {
		
		super(mensagem);
		
	}
	

	public LivroNotFoundException(String mensagem, Throwable causa) {
		
		super(mensagem,causa);
		
	}
	
}
