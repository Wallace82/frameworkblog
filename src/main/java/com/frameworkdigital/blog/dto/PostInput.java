package com.frameworkdigital.blog.dto;

import java.util.List;

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
public class PostInput {
	
	
	private String titulo;
	
	private String descricao;
	
	private List<String> links;
	
	private Long categoriaId;
	
	
	@FileSize(max = "1024KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private List<MultipartFile> imagens;
	
	


}