package com.jeronimokulandissa.cursomc.dto;

import java.io.Serializable;

import com.jeronimokulandissa.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	public CategoriaDTO() {}
	
	public CategoriaDTO(Categoria obj) // É usado para converter um objecto para um outro objecto ( Categoria para categoriaDTO)
	{
		id = obj.getId();
		nome = obj.getNome();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	
	
}
