package com.frameworkdigital.blog.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<Usuario> buscarUsuario(Long id) {
		return usuarioRepository.findById(id);
	}
	
	public List<Usuario> buscarUsuarios() {
		return usuarioRepository.findAll();
	}
	
}
