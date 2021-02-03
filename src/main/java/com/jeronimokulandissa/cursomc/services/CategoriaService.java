package com.jeronimokulandissa.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.repositories.CategoriaRepository;
import com.jeronimokulandissa.cursomc.resources.exception.ResourceExceptionHandler;


@Service
public class CategoriaService 
{
	
	/*Quando se declara uma dependência dentro de uma class e se coloca "Autowired"
	 * essa dependência será automáticamente instanciada pelo Spring usando (ID ou IC)
	 * */
	@Autowired 
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) 
	{
		Optional<Categoria> obj = repo.findById(id);
		
		/*
		if(obj == null) 
		{
			throw new ObjectNotFoundException("Objecto não encontrado! Id: "+id
								+", Tipo: "+Categoria.class.getName());
		}
		*/
			return obj.orElse(null);		
		
		//return obj.orElseThrow(() -> new ResourceExceptionHandler.ObjectNotFound(
		//	"Objecto não encontrado! Id: "+id+", Tipo: " + Categoria.class.getName()));
	}
}
