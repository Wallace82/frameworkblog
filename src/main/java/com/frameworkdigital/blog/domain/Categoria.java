package com.frameworkdigital.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "CATEGORIA_POST")
public class Categoria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CAT_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@Column(name="CAT_NOME")
	private String titulo;
	
	@Column(name="CAT_DESCRICAO")
	private String descricao;
	
	
	@Column(name="CAT_ATIVO")
	private boolean ativo;

	public Categoria(Long id, String titulo, String descricao, boolean ativo) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.ativo = ativo;
	}
	

}
