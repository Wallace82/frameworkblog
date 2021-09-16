package com.frameworkdigital.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frameworkdigital.blog.domain.GaleriaFotos;

@Repository
public interface GaleriaFotosRepository extends JpaRepository<GaleriaFotos, Long> {

}
