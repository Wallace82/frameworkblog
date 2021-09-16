package com.frameworkdigital.blog.controller;

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

import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.dto.PostDTO;
import com.frameworkdigital.blog.dto.UsuarioDTO;
import com.frameworkdigital.blog.mapper.MapperPost;
import com.frameworkdigital.blog.sevice.PostService;
import com.frameworkdigital.blog.sevice.UsuarioService;


@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private MapperPost mapperPost;
	
	@GetMapping("/{id}")
	public ResponseEntity<PostDTO>  getPost(@PathVariable Long id) {
		
		Optional<Post> optPost =  postService.buscarPost(id);
		
		PostDTO postDTO = mapperPost.mapperPost(optPost);
		
		return postDTO.getTitulo()!=null?ResponseEntity.ok(postDTO):ResponseEntity.notFound().build();
	}


	
	@GetMapping
	public ResponseEntity< List<PostDTO>>  getUsuarios() {
		
		List<Post> posts =  postService.buscarPosts();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<PostDTO> retorno = mapperPost.mapperPostList(posts, modelMapper);
		
		
		return retorno.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(retorno);
	}

	

}
