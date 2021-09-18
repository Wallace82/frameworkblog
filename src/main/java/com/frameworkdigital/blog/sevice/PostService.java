package com.frameworkdigital.blog.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frameworkdigital.blog.domain.Comentario;
import com.frameworkdigital.blog.domain.Curtidas;
import com.frameworkdigital.blog.domain.ImagensPost;
import com.frameworkdigital.blog.domain.Link;
import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.dto.ComentarioDTO;
import com.frameworkdigital.blog.exception.PostNaoEncontradoException;
import com.frameworkdigital.blog.filter.PostFilter;
import com.frameworkdigital.blog.mapper.MapperComentario;
import com.frameworkdigital.blog.repository.ComentarioRepository;
import com.frameworkdigital.blog.repository.CurtidasRepository;
import com.frameworkdigital.blog.repository.ImagensPostRepository;
import com.frameworkdigital.blog.repository.LinkRepository;
import com.frameworkdigital.blog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private LinkRepository linkRepository;
	
	@Autowired
	private ImagensPostRepository imagensPostRepository;
	@Autowired
	private CurtidasRepository curtidasRepository;
	
	@Autowired
	private MapperComentario mapperComentario;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public Post buscarPost(Long id) {
		return buscarOuFalhar(id);
	}
	
	
	public List<Post> buscarPosts() {
		return postRepository.findAll();
	}

	public Post cadastrar(Post post) {
		
		
		if(post.getDataHoraPublicacao()==null) {
			post.setDataHoraPublicacao(LocalDateTime.now());
		}
		
		return postRepository.save(post);
	}

	public Post buscarOuFalhar(Long postId) {
		return postRepository.findById(postId)
				.orElseThrow(() -> new PostNaoEncontradoException(postId));
	}
	
	
	public Post addLink(Link link) {
		
		link = linkRepository.save(link);
		
		return link.getPost();
	}
	
	
	public Post addImagem(ImagensPost imagensPost) {
		imagensPost = imagensPostRepository.save(imagensPost);
		return imagensPost.getPost();
	}


	public List<Post> buscarPostsParametro(String parametro,Pageable pageable) {
		
		PostFilter postFilter = new PostFilter();
		postFilter.setParametro(parametro);
		return postRepository.findByFilter(postFilter,pageable);
	}


	public void deletePost(Post post) {
		postRepository.delete(post);
	}


	public void curtir(Post post, Usuario usuario) {
		Curtidas  curtidas = new Curtidas(0l,LocalDateTime.now(),usuario,post,null);
		
		List<Curtidas> curtidasList = curtidasRepository.findByPostIdAndUsuarioId(post.getId(),usuario.getId());
		
		if(curtidasList.size()==0) {
			curtidasRepository.save(curtidas);
		}
	
	}
	
	public void comentar(ComentarioDTO comentarioDto) {
			Comentario comentario = mapperComentario.mapperDtoToEntity(comentarioDto);
			comentario.setDataHoraPublicacao(LocalDateTime.now());
			comentarioRepository.save(comentario);
	}
	

}
