package com.frameworkdigital.blog.sevice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frameworkdigital.blog.domain.Post;
import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.repository.UsuarioRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Optional<Post> buscarUsuario(Long id) {
		return postRepository.findById(id);
	}
	
	public List<Post> buscarUsuarios() {
		return postRepository.findAll();
	}

}
