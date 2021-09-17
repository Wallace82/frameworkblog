package com.frameworkdigital.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.frameworkdigital.blog.storage.FotoStorage;
import com.frameworkdigital.blog.storage.local.FotoStorageLocal;


@Configuration
public class FotoStorageConfig {
	
	@Bean
	public FotoStorage fotoStorage() {
		return new FotoStorageLocal();
	}
	
}
