package com.frameworkdigital.blog.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class SenhaDTO {
	
	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	private String novaSenha;	

}
