package com.jeronimokulandissa.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/* Esta class é um controlador Rest que irá responder por "/categorias" EndPoint */
@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource 
{
	@RequestMapping(method=RequestMethod.GET)
	public String listar() 
	{
		return "Rest está funcionando";
	}
}
