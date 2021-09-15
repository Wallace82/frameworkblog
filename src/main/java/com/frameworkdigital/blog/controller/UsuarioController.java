package com.frameworkdigital.blog.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.sevice.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public Usuario getUsuario(@PathVariable Long id) {
		
		Optional<Usuario> optUsuario =  usuarioService.buscarUsuario(id);
		
		System.err.println(optUsuario.get().getNome());
		return optUsuario.get();
	}
	
	

}
