package com.frameworkdigital.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frameworkdigital.blog.domain.Curtidas;

@Repository
public interface CurtidasRepository extends JpaRepository<Curtidas, Long> {

}
