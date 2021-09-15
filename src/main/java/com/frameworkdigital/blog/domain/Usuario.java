package com.frameworkdigital.blog.domain;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "USUARIO")
public class Usuario {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="USU_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@Column(name = "USU_DATACADASTRO")
	@DateTimeFormat(pattern="dd/MM/yyyy hh:MM:ss")
	private LocalDateTime dataHoraPublicacao;
	
	
	@Column(name="USU_NOME")
	private String nome;
	
	
	@Column(name="USU_EMAIL")
	private String email;
	
	
	@Column(name="USU_SENHA")
	private String senha;


	public Usuario(Long id, LocalDateTime dataHoraPublicacao, String nome, String email, String senha) {
		super();
		this.id = id;
		this.dataHoraPublicacao = dataHoraPublicacao;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}
	

}
