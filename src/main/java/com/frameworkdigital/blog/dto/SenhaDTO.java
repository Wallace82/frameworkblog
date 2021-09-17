package com.frameworkdigital.blog.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SenhaDTO {
	
	@NotBlank
	private String senhaAtual;
	
	@NotBlank
	private String novaSenha;	

}
