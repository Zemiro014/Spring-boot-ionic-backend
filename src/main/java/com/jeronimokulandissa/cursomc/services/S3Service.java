package com.jeronimokulandissa.cursomc.services;

import java.io.File;
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

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.jeronimokulandissa.cursomc.services.exceptions.FileException;

@Service
public class S3Service {
	private Logger LOG = LoggerFactory.getLogger(S3Service.class);

	@Autowired
	private AmazonS3 s3client;

	@Value("${s3.bucket}")
	private String bucketName;

	// Metodo usado para testes rápidos de enviar arquivo para S3 
	public void uploadFile(String localfile)
	{
		try {
		File file = new File(localfile);
		LOG.info("Iniciando upload");
		s3client.putObject(new PutObjectRequest(bucketName, "teste02.jpg", file));
		LOG.info("upload finalizado");
		}
		catch(AmazonServiceException e) 
		{
				LOG.info("AmazonServiceException: "+e.getErrorMessage());
		}
		catch (AmazonClientException e) {
			LOG.info("AmazonClientException: " +  e.getMessage());
		}
	}
	public URI uploadFile(MultipartFile multipartfile) {
		try {
		String fileName = multipartfile.getOriginalFilename(); // Extraindo o nome do arquivo que foi enviado pela requisição
		InputStream is = multipartfile.getInputStream(); // Encapsulando o processamento de leitura a apartir de uma origem, que é o meu arquivo
		String contentType = multipartfile.getContentType(); // Pega o tipo de conteúdo : se é uma imagem, arquivo ou outras opçoes
		return uploadFile(is, fileName, contentType);
		}
		catch(IOException e) 
		{
			throw new FileException("Erro de IO: " +e.getMessage());
		}
	}

	public URI uploadFile(InputStream is, String fileName, String contentType) {
		try {
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			LOG.info("Iniciando upload");
			s3client.putObject(bucketName, fileName, is, meta);
			LOG.info("upload finalizado");

			return s3client.getUrl(bucketName, fileName).toURI();
		} catch (URISyntaxException e) {
			throw new FileException("Erro ao converter URL para URI");
		}
	}
}
