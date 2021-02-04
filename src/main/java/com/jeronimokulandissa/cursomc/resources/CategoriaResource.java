package com.jeronimokulandissa.cursomc.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.services.CategoriaService;


/* Esta class é um controlador Rest que irá responder por "/categorias" EndPoint */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource 
{
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) 
	{
		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj)
	{
		/*
		 * O @RequestBody faz com que os dados "Json" que for enviado seja convertido em um objecto do tipo "Categoria"
		 * */
		obj = service.insert(obj);
		
		/*
		 * Boa prática de programação. Essa é a chamada que pega a URI do novo recurso que foi inserido
		 * */ 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id)
	{
		obj.setId(id); // Para o desencargo de consciência. Para garantir que o Id a ser atualizado é o Id que foi passado
		obj = service.update(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) 
	{
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
