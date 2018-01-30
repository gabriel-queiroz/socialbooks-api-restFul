package com.gabriel.socialapi.socialbooks.model.exceptions;

public class ComentarioNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ComentarioNotFoundException() {
		
		super("comentário não encontrado");
		
	}
}
