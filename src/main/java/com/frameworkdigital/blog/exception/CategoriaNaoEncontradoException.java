package com.frameworkdigital.blog.exception;


public class CategoriaNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public CategoriaNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public CategoriaNaoEncontradoException(Long postId) {
		this(String.format("Não existe um cadastro de categoria com código %d", postId));
	}
	
}
