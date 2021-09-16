package com.frameworkdigital.blog.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.dto.PostDTO;

@Component
public class MapperPost {

	
	public PostDTO mapperPost(Optional<Post> optPost) {
		ModelMapper modelMapper = new ModelMapper();
		PostDTO postDTO = modelMapper.map(optPost.orElse(new Post()), PostDTO.class);
		return postDTO;
	}
	
	public List<PostDTO> mapperPostList(List<Post> posts, ModelMapper modelMapper) {
		List<PostDTO> retorno = posts
				  .stream()
				  .map(user -> modelMapper.map(user, PostDTO.class))
				  .collect(Collectors.toList());
		return retorno;
	}
}
