package com.frameworkdigital.blog.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.dto.PostDTO;

@Component
public class MapperPost {

	
	@Autowired
	private ModelMapper modelMapper;
	
	
	public PostDTO mapperPost(Post post) {
		PostDTO postDTO = modelMapper.map(post, PostDTO.class);
		return postDTO;
	}
	
	public List<PostDTO> mapperPostList(List<Post> posts) {
		List<PostDTO> retorno = posts
				  .stream()
				  .map(post -> mapperPost(post))
				  .collect(Collectors.toList());
		return retorno;
	}

	public Post mapperPostDto(PostDTO postDTO) {
		ModelMapper modelMapper = new ModelMapper();
		Post post = modelMapper.map(postDTO, Post.class);
		return post;
	}
	
}