package com.jeronimokulandissa.cursomc.dto;

import java.io.Serializable;

import com.jeronimokulandissa.cursomc.domain.Producto;

public class ProductoDTO implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private double preco;
	
	public ProductoDTO() {}
	
	public ProductoDTO(Producto obj)
	{
		id = obj.getId();
		nome=obj.getNome();
		preco=obj.getPreco();
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
}
