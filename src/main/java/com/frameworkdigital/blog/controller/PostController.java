package com.frameworkdigital.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.frameworkdigital.blog.domain.ImagensPost;
import com.frameworkdigital.blog.domain.Link;
import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.dto.ArquivoDTO;
import com.frameworkdigital.blog.dto.FotoDTO;
import com.frameworkdigital.blog.dto.PostDTO;
import com.frameworkdigital.blog.dto.PostInput;
import com.frameworkdigital.blog.exception.PostNaoEncontradoException;
import com.frameworkdigital.blog.mapper.MapperPost;
import com.frameworkdigital.blog.sevice.PostService;
import com.frameworkdigital.blog.storage.FotoStorage;
import com.frameworkdigital.blog.storage.FotoStorageRunnable;


@RestController
@RequestMapping("/posts")
public class PostController {
	
	
	@Autowired
	private FotoStorage fotoStorage;
	
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
	
	
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  cadastroComFoto(@Valid PostInput postInput) throws IOException {

		PostDTO postDTO = mapperPost.mapperPostInput(postInput);
		postDTO.setId(0l);
		
		postDTO.setUsuarioId(1l);//usuario logado
		
		Post post = postService.cadastrar(mapperPost.mapperPostDto(postDTO));
		
		postInput.getImagens().stream().forEach(e -> 
				gravarImagem(new ImagensPost(0l,post,e.getOriginalFilename(),e.getContentType()),e));
		
		postInput.getLinks().stream().forEach(e -> 
				gravarLinks(new Link(0l,e,post)));
		
		
		return  buscar(post.getId());
		
	}


	


	private void gravarLinks(Link link) {
		postService.addLink(link);
	}


	private void gravarImagem(ImagensPost imagensPost,MultipartFile imagem) {
		FotoDTO retorno  = uploadFoto(imagem);
		imagensPost.setImagemNome(retorno.getNome());
		postService.addImagem(imagensPost);
		
	}
	
	
	@CrossOrigin(maxAge = 1800, origins = {"http://localhost:4200"})
	@PutMapping(name ="/imagens", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoDTO uploadFotoUnica( MultipartFile foto) {
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		String caminho = "ImgensPosts"+File.separator;
		Thread thread = new Thread(new FotoStorageRunnable(foto, resultado, fotoStorage,caminho));
		thread.start();
		FotoDTO retorno =null;
		while(retorno==null) {
			try { Thread.sleep (500); } catch (InterruptedException ex) {}
			retorno = (FotoDTO) resultado.getResult();
		}
		
		return retorno;
	}
	
	
	public FotoDTO uploadFoto( MultipartFile foto) {
		DeferredResult<FotoDTO> resultado = new DeferredResult<>();
		String caminho = "ImgensPosts"+File.separator;
		Thread thread = new Thread(new FotoStorageRunnable(foto, resultado, fotoStorage,caminho));
		thread.start();
		FotoDTO retorno =null;
		while(retorno==null) {
			try { Thread.sleep (500); } catch (InterruptedException ex) {}
			retorno = (FotoDTO) resultado.getResult();
		}
		
		return retorno;
	}
	

	

}
