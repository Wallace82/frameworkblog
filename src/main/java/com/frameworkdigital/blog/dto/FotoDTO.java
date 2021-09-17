package com.frameworkdigital.blog.dto;

public class FotoDTO {

	private String nome;
	private String contentType;
	private String diretorio;	
	
	public FotoDTO(String nome, String contentType,String diretorio) {
		this.nome = nome;
		this.contentType = contentType;
		this.diretorio = diretorio;
		
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getDiretorio() {
		return diretorio;
	}

	public void setDiretorio(String diretorio) {
		this.diretorio = diretorio;
	}
	

	
}
