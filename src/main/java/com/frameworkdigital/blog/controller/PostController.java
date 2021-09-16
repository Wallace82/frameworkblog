package com.frameworkdigital.blog.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.dto.PostDTO;
import com.frameworkdigital.blog.mapper.MapperPost;
import com.frameworkdigital.blog.sevice.PostService;


@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private MapperPost mapperPost;
	
	@GetMapping("/{id}")
	public PostDTO  getPost(@PathVariable Long id) {
		Post post =  postService.buscarOuFalhar(id);
		return mapperPost.mapperPost(post);
	}

	
	@GetMapping
	public List<PostDTO>  getPosts() {
		List<Post> posts =  postService.buscarPosts();
		return  mapperPost.mapperPostList(posts);
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public PostDTO inserirPost(@RequestBody @Validated PostDTO postDTO){
		Post post = postService.cadastrar(mapperPost.mapperPostDto(postDTO));
		PostDTO retorno  = getPost(post.getId());
		return retorno;
	}

	

}
