package com.jeronimokulandissa.cursomc.dto;

import java.io.Serializable;


// Este é a class que irá receber as requisições contendo as credenciais do usuário (usuário -> "email" e senha)
public class CredenciaisDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String email;
	private String senha;
	
	public CredenciaisDTO(){}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
