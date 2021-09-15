package com.frameworkdigital.blog.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.mapper.UsuarioDTO;
import com.frameworkdigital.blog.sevice.UsuarioService;


@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO>  getUsuario(@PathVariable Long id) {
		
		Optional<Usuario> optUsuario =  usuarioService.buscarUsuario(id);
		
		ModelMapper modelMapper = new ModelMapper();
		UsuarioDTO usuarioDTO = modelMapper.map(optUsuario.orElse(new Usuario()), UsuarioDTO.class);
		
		return usuarioDTO.getNome()!=null?ResponseEntity.ok(usuarioDTO):ResponseEntity.notFound().build();
	}
	
	
	@GetMapping
	public ResponseEntity< List<UsuarioDTO>>  getUsuarios() {
		
		List<Usuario> users =  usuarioService.buscarUsuarios();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<UsuarioDTO> retorno = users
				  .stream()
				  .map(user -> modelMapper.map(user, UsuarioDTO.class))
				  .collect(Collectors.toList());
		
		
		return retorno.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(retorno);
	}
	

}
