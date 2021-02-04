package com.jeronimokulandissa.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.jeronimokulandissa.cursomc.domain.Cliente;

// Este é uma class auxiliar para tranferir objectos do tipo Cliente
public class ClienteDTO implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	// Estes são os campos do objecto cliente que o usuário pode modificar
	private Integer id;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=120, message="O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Email(message="O formato do E-mail não é válido")
	private String email;
	
	public ClienteDTO() {}

	public ClienteDTO(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	// Criar um construtor que recebe um objecto do tipo "Cliente" para gerar um objecto do tipo "ClienteDTO"
	public ClienteDTO(Cliente obj) 
	{
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
	
}
