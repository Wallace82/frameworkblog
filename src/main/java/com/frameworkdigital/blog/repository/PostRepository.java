package com.frameworkdigital.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.frameworkdigital.blog.domain.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
