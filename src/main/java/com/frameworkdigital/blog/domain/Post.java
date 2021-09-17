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
@Table(name = "POST")
public class Post {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="POS_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@Column(name = "POS_DATACADASTRO")
	@DateTimeFormat(pattern="dd/MM/yyyy hh:MM:ss")
	private LocalDateTime dataHoraPublicacao;
	
	
	@Column(name="POS_TITULO",nullable = false)
	private String titulo;
	
	
	@Column(name="POS_DESCRICAO",nullable = false)
	private String descricao;
	
	@Column(name="POS_VISUALIZACOES")
	private int visualizacoes;
	
	
	@JoinColumn(name = "CAT_CODIGO", referencedColumnName = "CAT_CODIGO")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Categoria categoria;
	

	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Comentario> comentarios;
	
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Curtidas> curtidas;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<ImagensPost> imagensPost;
	
	@OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Link> links;
	
	@JoinColumn(name = "USU_CODIGO_POST", referencedColumnName = "USU_CODIGO")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Usuario usuario;

	@Override
	public String toString() {
		return "Post [" + (id != null ? "id=" + id + ", " : "")
				+ (dataHoraPublicacao != null ? "dataHoraPublicacao=" + dataHoraPublicacao + ", " : "")
				+ (titulo != null ? "titulo=" + titulo + ", " : "")
				+ (descricao != null ? "descricao=" + descricao + ", " : "") + "visualizacoes=" + visualizacoes + ", "
				+ (categoria != null ? "categoria=" + categoria + ", " : "")
				+ (comentarios != null ? "comentarios=" + comentarios + ", " : "")
				+ (curtidas != null ? "curtidas=" + curtidas + ", " : "")
				+ (imagensPost != null ? "imagensPost=" + imagensPost + ", " : "")
				+ (links != null ? "links=" + links + ", " : "") + (usuario != null ? "usuario=" + usuario : "") + "]";
	}

	
	
	
	
	
}
