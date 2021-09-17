package com.frameworkdigital.blog.security;


import java.util.Base64;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.frameworkdigital.blog.domain.Usuario;
import com.frameworkdigital.blog.repository.UsuarioRepository;



@Service
public class AuthProviderService implements AuthenticationProvider {

	

	@Autowired
	private PasswordEncoder passwordEncoder; 

	@Autowired
	private UsuarioRepository usuarioRepository;



	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {

		System.err.println();

		String senha = new String(Base64.getDecoder().decode(auth.getCredentials().toString().replaceAll("%3D","=")));
		String email =  auth.getName();

		System.err.println(">>>>>"+auth.getCredentials().toString());
		System.err.println(">>>>>"+email);

		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);


		if(usuario.isPresent()) {

			if(passwordEncoder.matches(senha.trim(),usuario.get().getSenha().trim())) {
				return new UsuarioSistema(usuario.get(), getPermissoes());
			}
			else {
				throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
			}
		}else {
			throw new UsernameNotFoundException("Login e/ou Senha inválidos.");
		}
	}






		private UsuarioSistema montarObjetoSessao(Usuario usuario,String senha) {

			//carregar as roles do usuário logado
			Collection<? extends GrantedAuthority> authorities = getPermissoes();

			//registra login de usuario
			//auditoriaLoginService.salvar(pessoa); 

			return new UsuarioSistema(usuario, getPermissoes());
		}







		@Override
		public boolean supports(Class<?> auth) {
			return auth.equals(UsernamePasswordAuthenticationToken.class);
		}


		private Collection<? extends GrantedAuthority> getPermissoes() {
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority("ROLE_EXCLUIR_POSTS"));
			authorities.add(new SimpleGrantedAuthority("ROLE_EXCLUIR_GALERIAS"));
			return authorities;
		}



	}
