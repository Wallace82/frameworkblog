package com.frameworkdigital.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frameworkdigital.blog.domain.Fotos;

@Repository
public interface FotosRepository extends JpaRepository<Fotos, Long> {

}
