package com.gabriel.socialapi.socialbooks.services.exceptions;

public class ComentarioJaExisteException  extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComentarioJaExisteException(String mensagem,Throwable causa) {
	
		super(mensagem,causa);
	}
	
	public ComentarioJaExisteException(String mensagem) {
		
		super(mensagem);
	}
	
}
