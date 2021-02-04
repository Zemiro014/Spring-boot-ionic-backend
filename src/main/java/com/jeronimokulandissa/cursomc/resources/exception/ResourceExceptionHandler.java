package com.jeronimokulandissa.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jeronimokulandissa.cursomc.services.exceptions.DataIntegrityException;
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
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
	}
	
	
	@ExceptionHandler(DataIntegrityException.class) // Indica que este método trata a exceção do tipo "ObjectNotFoundException"
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request)
	{
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
}
