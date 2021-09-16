package com.frameworkdigital.blog.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "PERMISSAO")
public class Permissao {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PER_CODIGO",unique=true, nullable=false)
	private Long id;
	
	@Column(name="PER_NOME",nullable = false)
	private String nome;
	
	@Column(name="PER_DESCRICAO",nullable = false)
	private String descricao;
	
}