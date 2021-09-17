package com.frameworkdigital.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.frameworkdigital.blog.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Long>{

}
