package com.frameworkdigital.blog.controller;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.sevice.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public Usuario getUsuario() {
		
		Optional<Usuario> optUsuario =  usuarioService.buscarUsuario(1l);
		return optUsuario.get();
	}
	
	

}
