package com.jeronimokulandissa.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.domain.Producto;
import com.jeronimokulandissa.cursomc.repositories.CategoriaRepository;
import com.jeronimokulandissa.cursomc.repositories.ProductoRepository;
import com.jeronimokulandissa.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class ProductoService 
{
	
	/*Quando se declara uma dependência dentro de uma class e se coloca "Autowired"
	 * essa dependência será automáticamente instanciada pelo Spring usando (ID ou IC)
	 * */
	@Autowired 
	private ProductoRepository productRepository;
	
	@Autowired 
	private CategoriaRepository categoriaRepository;
	
	public Producto find(Integer id) 
	{
		Optional<Producto> obj = productRepository.findById(id);
		
		/*
		if(obj == null) 
		{
			throw new ObjectNotFoundException("Objecto não encontrado! Id: "+id
								+", Tipo: "+Producto.class.getName());
		}
			return obj.orElse(null); */		
		// Se o objecto retornar nullo vai mostrar uma exception devidamente tratada
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Producto.class.getName()));
	}
	
	public Page<Producto> search(String nome, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy,  String direction)
	{
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return productRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}
}
