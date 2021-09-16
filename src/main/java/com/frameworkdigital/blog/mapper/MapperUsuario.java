package com.frameworkdigital.blog.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.dto.UsuarioDTO;

@Component
public class MapperUsuario {

	
	public UsuarioDTO mapperUsuario(Optional<Usuario> optUsuario) {
		ModelMapper modelMapper = new ModelMapper();
		UsuarioDTO postDTO = modelMapper.map(optUsuario.orElse(new Usuario()), UsuarioDTO.class);
		return postDTO;
	}
	
	public List<UsuarioDTO> mapperPostList(List<Usuario> posts, ModelMapper modelMapper) {
		List<UsuarioDTO> retorno = posts
				  .stream()
				  .map(user -> modelMapper.map(user, UsuarioDTO.class))
				  .collect(Collectors.toList());
		return retorno;
	}
}
