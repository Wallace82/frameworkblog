package com.frameworkdigital.blog.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.frameworkdigital.blog.core.validation.FileContentType;
import com.frameworkdigital.blog.core.validation.FileSize;

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
	
	private List<LinkDTO> links;
	
	@FileSize(max = "1024KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private List<MultipartFile> imagens;
	
	
	private String action = "posts";
	
	public String getAction() {
		return "/"+action+"/"+id;
	}


}
