package com.frameworkdigital.blog.dto;

import java.time.LocalDateTime;
import java.util.List;

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
	
	private Long categoriaId;
	
	private Long usuarioId;
	
	private List<LinkDTO> links;
	
	
	private List<ImagemPostDTO> imagens;
	
	
	private String action = "posts";
	
	public String getAction() {
		return "/"+action+"/"+id;
	}

	@Override
	public String toString() {
		return "PostDTO [id=" + id + ", "
				+ (dataHoraPublicacao != null ? "dataHoraPublicacao=" + dataHoraPublicacao + ", " : "")
				+ (titulo != null ? "titulo=" + titulo + ", " : "")
				+ (descricao != null ? "descricao=" + descricao + ", " : "") + "visualizacoes=" + visualizacoes + ", "
				+ (categoriaId != null ? "categoriaId=" + categoriaId + ", " : "")
				+ (usuarioId != null ? "usuarioId=" + usuarioId + ", " : "")
				+ (links != null ? "links=" + links + ", " : "") + (imagens != null ? "imagens=" + imagens + ", " : "")
				+ (action != null ? "action=" + action : "") + "]";
	}
	
	


}
