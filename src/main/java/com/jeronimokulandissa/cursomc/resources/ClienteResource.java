package com.jeronimokulandissa.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.services.ClienteService;


/* Esta class é um controlador Rest que irá responder por "/categorias" EndPoint */
@RestController
@RequestMapping(value="/clientes") 
public class ClienteResource 
{
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET) 
	public ResponseEntity<?> find(@PathVariable Integer id)  // O "/clientes/{id}" é o EndPoint para consumir o que o método "find" do "ClienteResource" oferece 
	{
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
	}
}
