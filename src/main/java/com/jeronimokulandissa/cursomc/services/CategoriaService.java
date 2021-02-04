package com.jeronimokulandissa.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.repositories.CategoriaRepository;
import com.jeronimokulandissa.cursomc.services.exceptions.DataIntegrityException;
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
	
	public Categoria update (Categoria obj) 
	{
		find(obj.getId()); // Verifica se o Id realmente existe
		return repo.save(obj);
	}
	
	public void delete(Integer id) 
	{
		find(id); // Verifica se o Id realmente existe. Caso não exista, retorna a excessão
		
		try 
		{
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) 
		{
			throw new DataIntegrityException("Não é possível excluir uma categoria que possui productos");
		}
		
	}
	
	public List<Categoria> findAll()
	{
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy,  String direction)
	{
		PageRequest pagerequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		
		return repo.findAll(pagerequest);
		
	}
}
