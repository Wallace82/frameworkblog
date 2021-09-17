package com.frameworkdigital.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.frameworkdigital.blog.domain.ImagensPost;
import com.frameworkdigital.blog.domain.Link;
import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.dto.PostDTO;
import com.frameworkdigital.blog.dto.PostInput;
import com.frameworkdigital.blog.exception.PostNaoEncontradoException;
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
	public ResponseEntity<?>  buscar(@PathVariable Long id) {
		try {
			Post post =  postService.buscarPost(id);
			 return ResponseEntity.ok(mapperPost.mapperPost(post));
		} catch (PostNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
	@GetMapping
	public List<PostDTO>  listar() {
		List<Post> posts =  postService.buscarPosts();
		return  mapperPost.mapperPostList(posts);
	}
	
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public PostDTO cadastrar(@RequestBody @Valid PostDTO postDTO){
//		Post post = postService.cadastrar(mapperPost.mapperPostDto(postDTO));
//		PostDTO retorno  = mapperPost.mapperPost(postService.buscarPost(post.getId()));
//		return retorno;
//	}
	
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  cadastroComFoto(@Valid PostInput postInput,List<MultipartFile> imagens) throws IOException {

		PostDTO postDTO = mapperPost.mapperPostInput(postInput);
		postDTO.setUsuarioId(1l);//usuario logado
		
		System.err.println("DTO -> "+ postDTO.toString());
		
		Post post = postService.cadastrar(mapperPost.mapperPostDto(postDTO));
		
		System.err.println("obj -> "+ post.toString());
		
		postInput.getImagens().stream().forEach(e -> 
				gravarImagem(new ImagensPost(0l,post,e.getOriginalFilename(),e.getContentType())));
		
		postInput.getLinks().stream().forEach(e -> 
				gravarLinks(new Link(0l,e,post)));
		
		
		
		
		return  ResponseEntity.ok("SUCESSO");
		
	}


	private void gravarLinks(Link link) {
		System.err.println("GRAVANDO link NO BANCO >"+link.toString());
	}


	private void gravarImagem(ImagensPost imagensPost) {
		
		System.err.println("GRAVANDO REFE IMAGEN NO BANCO >"+imagensPost.toString());
		
	}
	
	

	

}
