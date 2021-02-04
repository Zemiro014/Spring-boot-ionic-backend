package com.jeronimokulandissa.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.repositories.CategoriaRepository;
import com.jeronimokulandissa.cursomc.services.exceptions.ObjectNotFoundException;


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
			return obj.orElse(null); */		
		// Se o objecto retornar nullo vai mostrar uma exception devidamente tratada
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria insert(Categoria obj) 
	{
		obj.setId(null); // Garante que o obj a ser inserido tem o Id null. Se o obj tem Id não nullo, então o método "repo.save(obj)" vai considerar que é uma atualização e não inserção
		return repo.save(obj);
	}
}
