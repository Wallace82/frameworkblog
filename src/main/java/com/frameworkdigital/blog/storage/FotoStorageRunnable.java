package com.frameworkdigital.blog.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.frameworkdigital.blog.dto.FotoDTO;


public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile files;
	private DeferredResult<FotoDTO> resultado;
	private FotoStorage fotoStorage;
	private String local; 
	
	public FotoStorageRunnable(MultipartFile files, DeferredResult<FotoDTO> resultado, FotoStorage fotoStorage,String local) {
		this.files = files;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
		this.local = local;
	}

	@Override
	public void run() {
		String nomeFoto = this.fotoStorage.salvarTemporariamente(files,this.local);
		String contentType = files.getContentType();
		resultado.setResult(new FotoDTO(nomeFoto, contentType,this.local));
	}

}
