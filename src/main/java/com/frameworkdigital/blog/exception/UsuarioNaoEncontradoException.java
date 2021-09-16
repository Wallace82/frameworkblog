package com.frameworkdigital.blog.exception;


public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Long postId) {
		this(String.format("Não existe um cadastro de usuário com código %d", postId));
	}
	
}
