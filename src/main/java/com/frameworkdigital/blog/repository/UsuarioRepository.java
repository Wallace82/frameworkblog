package com.frameworkdigital.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frameworkdigital.blog.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
