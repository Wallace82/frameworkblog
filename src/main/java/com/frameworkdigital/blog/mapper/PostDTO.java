package com.frameworkdigital.blog.mapper;

import java.time.LocalDateTime;

import javax.persistence.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	
	private long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy hh:MM:ss")
	private LocalDateTime dataHoraPublicacao;
	
	private String titulo;
	
	private String descricao;
	
	private int visualizacoes;
	
	private Long categoria;
	
	private Long usuario;
	

}
