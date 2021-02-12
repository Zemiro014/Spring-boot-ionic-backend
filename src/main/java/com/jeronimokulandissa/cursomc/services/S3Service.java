package com.jeronimokulandissa.cursomc.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

@Service
public class S3Service {
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	public URI uploadFile(MultipartFile multipartfile) {
		try {
		String fileName = multipartfile.getOriginalFilename(); // Extraindo o nome do arquivo que foi enviado pela requisição
		InputStream is = multipartfile.getInputStream(); // Encapsulando o processamento de leitura a apartir de uma origem, que é o meu arquivo
		String contentType = multipartfile.getContentType(); // Pega o tipo de conteúdo : se é uma imagem, arquivo ou outras opçoes
		return uploadFile(is, fileName, contentType);
		}
		catch(IOException e) 
		{
			throw new RuntimeException("Erro de IO: " +e.getMessage());
		}
	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando upload");
			s3client.putObject(fileName, contentType, is, meta);
			LOG.info("upload finalizado");

			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new RuntimeException("Erro ao converter URL para URI");
		}
	}
}
