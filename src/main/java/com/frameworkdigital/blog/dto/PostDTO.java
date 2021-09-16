package com.frameworkdigital.blog.dto;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PostDTO {
	
	private long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy hh:MM:ss")
	private LocalDateTime dataHoraPublicacao;
	
	private String titulo;
	
	private String descricao;
	
	private int visualizacoes;
	
	private Long categoriaId;
	
	private Long usuarioId;
	
	private String action = "posts";
	
	public String getAction() {
		return "/"+action+"/"+id;
	}


}
