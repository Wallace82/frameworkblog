package com.frameworkdigital.blog.exception;


public class GeleriFotosNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public GeleriFotosNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public GeleriFotosNaoEncontradoException(Long postId) {
		this(String.format("Não existe uma galeria de fotos com código %d", postId));
	}
	
}
