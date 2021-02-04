package com.jeronimokulandissa.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jeronimokulandissa.cursomc.domain.Producto;
import com.jeronimokulandissa.cursomc.dto.ProductoDTO;
import com.jeronimokulandissa.cursomc.resources.utils.URL;
import com.jeronimokulandissa.cursomc.services.ProductoService;


/* Esta class é um controlador Rest que irá responder por "/pedidos" EndPoint */
@RestController
@RequestMapping(value="/productos")
public class ProductoResource 
{
	@Autowired
	private ProductoService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Producto> find(@PathVariable Integer id) 
	{
		Producto obj = service.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	/*
	 * Realizando Busca de dados por paginação
	 * */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProductoDTO>> findPage(	
														@RequestParam(value="nome", defaultValue="") String nome,
														@RequestParam(value="categorias", defaultValue="") String categorias,
														@RequestParam(value="page", defaultValue="0") Integer page, 
														@RequestParam(value="linesPerPage", defaultValue="24") Integer linesPerPage, 
														@RequestParam(value="orderBy", defaultValue="nome") String orderBy,  
														@RequestParam(value="direction", defaultValue="ASC") String direction) 
	{
		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecoded = URL.decodeParam(nome);
		Page<Producto> list = service.search(nomeDecoded, ids, page, linesPerPage, orderBy,  direction); // Busca um Page de Categorias no banco de dados e depois esse Page é convertido para Page CategoriaDTO pelo código abaixo
		Page<ProductoDTO> listDTO = list.map(obj -> new ProductoDTO(obj)); // Convertendo um Page  para outro Page
		return ResponseEntity.ok().body(listDTO);
	}
}
