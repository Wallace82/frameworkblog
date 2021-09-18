package com.frameworkdigital.blog.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.frameworkdigital.blog.domain.Comentario;
import com.frameworkdigital.blog.domain.ImagensPost;
import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.dto.ComentarioDTO;
import com.frameworkdigital.blog.dto.ComentarioPostInput;
import com.frameworkdigital.blog.dto.ImagemPostDTO;
import com.frameworkdigital.blog.dto.PostDTO;
import com.frameworkdigital.blog.dto.PostGaleriaInput;
import com.frameworkdigital.blog.exception.PostNaoEncontradoException;
import com.frameworkdigital.blog.mapper.MapperComentario;
import com.frameworkdigital.blog.mapper.MapperPost;
import com.frameworkdigital.blog.page.Paginacao;
import com.frameworkdigital.blog.repository.ComentarioRepository;
import com.frameworkdigital.blog.sevice.PostService;
import com.frameworkdigital.blog.sevice.UsuarioService;
import com.frameworkdigital.blog.storage.FotoStorage;
import com.frameworkdigital.blog.storage.FotoStorageRunnable;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/galerias")
@CrossOrigin(maxAge = 1800, origins = {"http://localhost:4200"})
public class GaleriaController {


	@Autowired
	private FotoStorage fotoStorage;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private MapperPost mapperPost;
	
	@Autowired
	private MapperComentario mapperComentario;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	private static final int tipoPostagem = 2;
	
	@GetMapping("/{id}")
	public ResponseEntity<?>  buscar(@PathVariable Long id) {
		try {
			 Post post =  postService.buscarPost(id);
			 postService.contabilizarVisualizacao(post);
			 return ResponseEntity.ok(mapperPost.mapperPost(post));
		} catch (PostNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Cadastra uma curtida para uma galeria selecionada")
	@PutMapping(path = "/curtir/{id}",  name ="curtir" )
		public ResponseEntity<?>  curtir(@PathVariable Long id) {
		try {
			Post post =  postService.buscarPost(id);
			Usuario  usuario = usuarioService.buscarUsuario(1l);
			
			postService.curtir(post,usuario);
			
			return ResponseEntity.ok(mapperPost.mapperPost(post));
		} catch (PostNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "Cadastra uma comentário uma galeria selecionada")
	@PutMapping(path = "/comentar",  name ="comentar" )
		public ResponseEntity<?>  comentar(@RequestBody @Valid ComentarioPostInput comentarioPostInput ) {
		try {
			Usuario  usuario = usuarioService.buscarUsuario(1l);
			
			ComentarioDTO comentarioDTO =  mapperComentario.mapperInput(comentarioPostInput);
			comentarioDTO.setUsuarioId(usuario.getId());
			postService.comentar(comentarioDTO);
			
			Post post =  postService.buscarPost(comentarioDTO.getPostId());
			return ResponseEntity.ok(mapperPost.mapperPost(post));
		} catch (PostNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "Exclui um comentário para uma galeria selecionada")
	@DeleteMapping(path = "/excluir/comentario/{id}"   )
		public ResponseEntity<?>  excluircomentar(@PathVariable Long id) {
		try {
			
			Optional<Comentario> comentarioOpt =  comentarioRepository.findById(id);
			
			Usuario  usuario = usuarioService.buscarUsuario(1l);
			
			if(usuario.getId().equals(comentarioOpt.get().getUsuario().getId())) {
				postService.excluirComentario(comentarioOpt.get());
			}
			else {
				return new ResponseEntity<>("SEM PERMISSAO PARA EXCLUIR ESTE COMENTÁRIO", HttpStatus.NOT_FOUND);
			}
			
			Post post =  postService.buscarPost(comentarioOpt.get().getPost().getId());
			return ResponseEntity.ok(mapperPost.mapperPost(post));
			
		} catch (PostNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "Retorna uma lista de comentários uma galeria selecionada")
	@GetMapping("/comentarios/{id}")
	public ResponseEntity<?>  listaComentarios(@PathVariable Long id) {
		try {
			 Post post =  postService.buscarPost(id);
			 
			 return ResponseEntity.ok(mapperComentario.mapperList(post.getComentarios()));
			 
		} catch (PostNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>(msg.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	
	@ApiOperation(value = "Retorna uma lista de galerias conforme filtro")
	@GetMapping
	public ResponseEntity<Page<PostDTO>>  filtrar(
			@RequestParam(value = "search[value]", required=false) String parametro,
			Pageable pageable
			) {
		
		List<Post> posts =  postService.buscarPostsParametro(parametro,pageable);
		List<PostDTO> retorno = mapperPost.mapperPostList(posts);
		Page<PostDTO> page = new PageImpl<>(retorno);
		return ResponseEntity.ok().body(page) ;
	}
	
	
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  cadastroComFoto(@RequestBody @Valid PostGaleriaInput postInput) throws IOException {

		PostDTO postDTO = mapperPost.mapperGleriaPostInput(postInput);
		postDTO.setId(0l);
		postDTO.setTipoPostagem(tipoPostagem);
		
		postDTO.setUsuarioId(1l);//usuario logado
		
		Post post = postService.cadastrar(mapperPost.mapperPostDto(postDTO));
		
		postInput.getImagens().stream().forEach(e -> 
				gravarImagem(new ImagensPost(0l,post,e,e)));
		
		
		
		return  buscar(post.getId());
		
	}
	
	@DeleteMapping()
	public ResponseEntity<?>  excluir(Long id) throws IOException {
		Usuario usuario = usuarioService.buscarUsuario(1l);
		try {
			Post post =  postService.buscarPost(id);
			
			if(post.getUsuario().getId().equals(usuario.getId())) {
				postService.deletePost(post);
			}
			else {
				return new ResponseEntity<>("SEM PERMISSAO PARA EXCLUIR ESTA GALERIA", HttpStatus.NOT_FOUND);
			}

			return ResponseEntity.ok("Excluido com sucesso");
		} catch (PostNaoEncontradoException msg) {
			ResponseEntity.notFound().build();
			return new ResponseEntity<>("Erro ao excluir GALERIA ", HttpStatus.NOT_FOUND);
		}
		
		
	}

	
	@CrossOrigin(maxAge = 1800, origins = {"http://localhost:4200"})
	@PutMapping(path = "/imagens",  name ="imagens", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ImagemPostDTO uploadFotoUnica( MultipartFile foto) {
		DeferredResult<ImagemPostDTO> resultado = new DeferredResult<>();
		String caminho = "ImgensPosts"+File.separator;
		Thread thread = new Thread(new FotoStorageRunnable(foto, resultado, fotoStorage,caminho));
		thread.start();
		ImagemPostDTO retorno =null;
		while(retorno==null) {
			try { Thread.sleep (500); } catch (InterruptedException ex) {}
			retorno = (ImagemPostDTO) resultado.getResult();
		}
		
		return retorno;
	}
	
	
	
	
	private void gravarImagem(ImagensPost imagensPost) {
		postService.addImagem(imagensPost);
		
	}
	

	
	
	
}
