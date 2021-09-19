package com.frameworkdigital.blog.domain.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.frameworkdigital.blog.domain.exception.NegocioException;
import com.frameworkdigital.blog.domain.exception.UsuarioNaoEncontradoException;
import com.frameworkdigital.blog.domain.model.Usuario;
import com.frameworkdigital.blog.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario buscarUsuario(Long id) {
		return buscarOuFalhar(id);
	}
	
	public List<Usuario> buscarUsuarios() {
		return usuarioRepository.findAll();
	}
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return usuarioRepository.findById(usuarioId)
				.orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
	}
	
	
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		
		Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
		if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o e-mail %s", usuario.getEmail()));
		}
		
		usuario.setDataCadastro(LocalDate.now());
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
		Usuario usuario = buscarOuFalhar(usuarioId);
		
		if (usuario.senhaNaoCoincideCom(senhaAtual)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
		}
		
		usuario.setSenha(novaSenha);
	}

	
}
