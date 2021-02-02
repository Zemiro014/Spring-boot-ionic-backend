package com.jeronimokulandissa.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService 
{
	
	/*Quando se declara uma dependência dentro de uma class e se coloca "Autowired"
	 * essa dependência será automáticamente instanciada pelo Spring usando (ID ou IC)
	 * */
	@Autowired 
	private CategoriaRepository repo;
	public Categoria buscar(Integer id) 
	{
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElse(null);
	}
}
