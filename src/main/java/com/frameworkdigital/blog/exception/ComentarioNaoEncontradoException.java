package com.frameworkdigital.blog.exception;


public class ComentarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ComentarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ComentarioNaoEncontradoException(Long postId) {
		this(String.format("Não existe um cadastro comentário com código %d", postId));
	}
	
}
