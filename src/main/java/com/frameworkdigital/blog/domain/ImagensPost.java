package com.frameworkdigital.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "IMAGENS_POST")
public class ImagensPost {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="IPO_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@JoinColumn(name = "POS_CODIGO", referencedColumnName = "POS_CODIGO")
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private Post post;

	@Column(name = "IPO_NOME_IMAGEM")
  	private String imagenNome;
  	
  	@Column(name = "IPO_IMAGEM_CONTENTTYPE")
  	private String imagenContentType;

	
}
