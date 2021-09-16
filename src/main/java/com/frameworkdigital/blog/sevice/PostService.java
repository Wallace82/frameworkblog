package com.frameworkdigital.blog.sevice;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.exception.PostNaoEncontradoException;
import com.frameworkdigital.blog.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
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
	

}
