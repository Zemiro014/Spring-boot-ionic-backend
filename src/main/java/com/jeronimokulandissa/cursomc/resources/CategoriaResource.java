package com.jeronimokulandissa.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.dto.CategoriaDTO;
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
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto)
	{		
		/*
		 * O @RequestBody faz com que os dados "Json" que for enviado seja convertido em um objecto do tipo "Categoria"
		 * O "@Valid" serve para validar o "objDto"
		 * */
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);		
		/*
		 * Boa prática de programação. Essa é a chamada que pega a URI do novo recurso que foi inserido
		 * */ 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id)
	{
		Categoria obj = service.fromDTO(objDto);
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
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() 
	{
		List<Categoria> list = service.findAll(); // Buscaum List de Categorias no banco de dados e depois esse List é convertido para List CategoriaDTO pelo código abaixo
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList()); // Convertendo um List  para outro List
		return ResponseEntity.ok().body(listDTO);
	}
	
	
	/*
	 * Realizando Busca de dados por paginação
	 * */
	@RequestMapping(value="/page",method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(	
														@RequestParam(value="page", defaultValue="0") Integer page, 
														@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
														@RequestParam(value="orderBy", defaultValue="nome") String orderBy,  
														@RequestParam(value="direction", defaultValue="ASC") String direction) 
	{
		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy,  direction); // Busca um Page de Categorias no banco de dados e depois esse Page é convertido para Page CategoriaDTO pelo código abaixo
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj)); // Convertendo um Page  para outro Page
		return ResponseEntity.ok().body(listDTO);
	}
	
}
