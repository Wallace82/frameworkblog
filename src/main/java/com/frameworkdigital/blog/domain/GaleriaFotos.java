package com.frameworkdigital.blog.domain;


import java.time.LocalDateTime;
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

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "GALERIAFOTOS")
public class GaleriaFotos {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="GAF_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@Column(name = "GAF_DATACADASTRO")
	@DateTimeFormat(pattern="dd/MM/yyyy hh:MM:ss")
	private LocalDateTime dataHoraPublicacao;
	
	
	@Column(name="GAF_TITULO")
	private String titulo;
	
	
	@Column(name="GAF_DESCRICAO")
	private String descricao;
	

	
	@JoinColumn(name = "USU_CODIGO_GALERIA", referencedColumnName = "USU_CODIGO")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Usuario usuario;

	
	@OneToMany(mappedBy = "galeriaFotos", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Fotos> fotos;


	public GaleriaFotos(Long id, LocalDateTime dataHoraPublicacao, String titulo, String descricao, Usuario usuario,
			List<Fotos> fotos) {
		super();
		this.id = id;
		this.dataHoraPublicacao = dataHoraPublicacao;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.fotos = fotos;
	}
	
	
}
