package com.jeronimokulandissa.cursomc.domain;

import java.io.Serializable;

public class Categoria implements Serializable
{
	/*Serializable é uma interface que difine se os objectos de uma class poderão ser convertidos para uma sequência de Bytes
	 * Isso é importante pois permite que os objectos sejam gravados em arquivos, trafegar em redes e assim por diante. É uma exigência em Java
	 * Toda class que implementa a interface Serializable precisa de um número de versão padrão*/
	
	private static final long serialVersionUID = 1L; // número de versão padrão
	
	private Integer id;
	private String nome;
	
	public Categoria() {		
	}

	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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

	// HashCodeEqual: Serve para comparar os objectos por valor e não pelo ponteiro de memória. É a implementação padrão de comparação de objectos. Normalmente a comparação é feita usando o Id
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
	
}
