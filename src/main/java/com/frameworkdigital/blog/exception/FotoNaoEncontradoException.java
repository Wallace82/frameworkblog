package com.frameworkdigital.blog.exception;


public class FotoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public FotoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public FotoNaoEncontradoException(Long postId) {
		this(String.format("Não existe um cadastro de foto com código %d", postId));
	}
	
}
