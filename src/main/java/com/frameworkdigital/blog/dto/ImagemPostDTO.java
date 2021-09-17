package com.frameworkdigital.blog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImagemPostDTO {
	
	private Long id;
	private Long postId;
  	private String imagemNome;
  	private String imagemContentType;
  	
  	private String action="imagenspost";
  	
  	public String getAction() {
  		return "/"+action+"/"+id;
  	}
	
}
