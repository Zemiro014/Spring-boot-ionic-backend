package com.jeronimokulandissa.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.jeronimokulandissa.cursomc.services.exceptions.AuthorizationException;
import com.jeronimokulandissa.cursomc.services.exceptions.DataIntegrityException;
import com.jeronimokulandissa.cursomc.services.exceptions.FileException;
import com.jeronimokulandissa.cursomc.services.exceptions.ObjectNotFoundException;

/*
 * É uma class auxiliar que vai interceptar as Exceções
 * */
@ControllerAdvice
public class ResourceExceptionHandler 
{
	
	@ExceptionHandler(ObjectNotFoundException.class) // Indica que este método trata a exceção do tipo "ObjectNotFoundException"
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request)
	{
		StandardError err =  new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class) // Indica que este método trata a exceção do tipo "DataIntegrityException"
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request)
	{
		StandardError err =  new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(), "Integridade de dados", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class) // Indica que este método trata a exceção do tipo "MethodArgumentNotValidException": Para valição de campos
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request)
	{
		ValidationError err = new ValidationError(System.currentTimeMillis(),HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", e.getMessage(), request.getRequestURI());
		
		// Varrer a lista de exceção "e" e pegar todos os campos de informação de erro
		for(FieldError x : e.getBindingResult().getFieldErrors()) 
		{
			err.addError(x.getField(), x.getDefaultMessage());
		}		
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);
	}
	
	@ExceptionHandler(AuthorizationException.class) // Indica que este método trata a exceção do tipo "AuthorizationException"
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request)
	{
		StandardError err =  new StandardError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(), "Acesso negado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}
	
	
	/*Manipulador de Exceções do arquivo*/
	@ExceptionHandler(FileException.class) // Indica que este método trata a exceção do tipo "AuthorizationException"
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request)
	{
		StandardError err =  new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(), "Erro de arquivo", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	/*Manipulador de Exceções da Amazon*/
	@ExceptionHandler(AmazonServiceException.class) // Indica que este método trata a exceção do tipo "AuthorizationException"
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request)
	{
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		StandardError err =  new StandardError(System.currentTimeMillis(),code.value(), "Erro Amazon Service", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(code).body(err);
	}
	
	@ExceptionHandler(AmazonClientException.class) // Indica que este método trata a exceção do tipo "AuthorizationException"
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request)
	{
		StandardError err =  new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(), "Erro Amazon Cliente", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	@ExceptionHandler(AmazonS3Exception.class) // Indica que este método trata a exceção do tipo "AuthorizationException"
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request)
	{
		StandardError err =  new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(), "Erro Amazon S3", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
