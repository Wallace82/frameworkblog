package com.frameworkdigital.blog.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frameworkdigital.blog.domain.Comentario;
import com.frameworkdigital.blog.dto.ComentarioDTO;
import com.frameworkdigital.blog.dto.ComentarioPostInput;
import com.frameworkdigital.blog.dto.PostDTO;
import com.frameworkdigital.blog.dto.PostInput;

@Component
public class MapperComentario {

	@Autowired
	private ModelMapper modelMapper;
	
	public ComentarioDTO mapperEntityToDto(Comentario comentario) {
		ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
		return comentarioDTO;
	}
	
	public List<ComentarioDTO> mapperList(List<Comentario> comentarios) {
		List<ComentarioDTO> retorno = comentarios
				  .stream()
				  .map(cat -> mapperEntityToDto(cat))
				  .collect(Collectors.toList());
		return retorno;
	}
	
	public Comentario mapperDtoToEntity(ComentarioDTO comentarioDTO) {
		return modelMapper.map(comentarioDTO, Comentario.class);
	}
	
	public void copyDtoToEntity(ComentarioDTO comentarioDTO, Comentario comentario) {
		modelMapper.map(comentarioDTO, comentario);
	}
	
	public ComentarioDTO mapperInput(ComentarioPostInput input) {
		return modelMapper.map(input, ComentarioDTO.class);
	}
}
