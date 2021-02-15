package com.jeronimokulandissa.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.Estado;
import com.jeronimokulandissa.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService 
{

	@Autowired
	private EstadoRepository estadoRepoository;
	
	public List<Estado> findAll()
	{
		return estadoRepoository.findAllByOrderByNome();
	}
}
