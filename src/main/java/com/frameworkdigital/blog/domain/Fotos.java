package com.frameworkdigital.blog.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "FOTOS")
public class Fotos {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FOT_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@Column(name="FOT_DESCRICAO")
	private String descricao;
	
	
	@JoinColumn(name = "GAF_CODIGO", referencedColumnName = "GAF_CODIGO")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private GaleriaFotos galeriaFotos;

	
	@OneToMany(mappedBy = "foto", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Comentario> comentarios;
	
	
	@OneToMany(mappedBy = "foto", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Curtidas> curtidas;
	
	
	@Column(name="FOT_VISUALIZACOES")
	private int visualizacoes;
	
	@Column(name = "FOT_NOME")
  	private String nome;
  	
  	@Column(name = "FOT_CONTENTTYPE")
  	private String contentType;

	public Fotos(Long id, String descricao, GaleriaFotos galeriaFotos, List<Comentario> comentarios,
			List<Curtidas> curtidas, int visualizacoes, String nome, String contentType) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.galeriaFotos = galeriaFotos;
		this.comentarios = comentarios;
		this.curtidas = curtidas;
		this.visualizacoes = visualizacoes;
		this.nome = nome;
		this.contentType = contentType;
	}
  	
}
