package com.frameworkdigital.blog.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.frameworkdigital.blog.domain.ImagensPost;
import com.frameworkdigital.blog.domain.Link;
import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.exception.PostNaoEncontradoException;
import com.frameworkdigital.blog.filter.PostFilter;
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
	

}
