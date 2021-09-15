package com.frameworkdigital.blog.mapper;

import java.time.LocalDateTime;


import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ComentarioDTO {
	
	private Long id;
	
	@DateTimeFormat(pattern="dd/MM/yyyy hh:MM:ss")
	private LocalDateTime dataHoraPublicacao;
	
	private Long usuario;

	private Long post;
	
	private Long foto;

	public ComentarioDTO(Long id, LocalDateTime dataHoraPublicacao, Long usuario, Long post, Long foto) {
		super();
		this.id = id;
		this.dataHoraPublicacao = dataHoraPublicacao;
		this.usuario = usuario;
		this.post = post;
		this.foto = foto;
	}

}
