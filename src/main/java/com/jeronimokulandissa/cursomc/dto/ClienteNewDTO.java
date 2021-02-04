package com.jeronimokulandissa.cursomc.dto;

import java.io.Serializable;

// Esta é uma class DTO auxiliar para permitir cadastrar um Cliente com as seguintes dependências: Endereco, Telefone e Cidade
public class ClienteNewDTO implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;
	
	private String logradoura;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	private String telefone;
	private String telefoneOpcao1;
	private String telefoneOpcao2;
	
	private Integer cidadeId;
	
	public ClienteNewDTO() {}

	public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, String logradoura, String numero,
			String complemento, String bairro, String cep, String telefone, String telefoneOpcao1,
			String telefoneOpcao2, Integer cidadeId) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.logradoura = logradoura;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefone = telefone;
		this.telefoneOpcao1 = telefoneOpcao1;
		this.telefoneOpcao2 = telefoneOpcao2;
		this.cidadeId = cidadeId;
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradoura() {
		return logradoura;
	}

	public void setLogradoura(String logradoura) {
		this.logradoura = logradoura;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getTelefoneOpcao1() {
		return telefoneOpcao1;
	}

	public void setTelefoneOpcao1(String telefoneOpcao1) {
		this.telefoneOpcao1 = telefoneOpcao1;
	}

	public String getTelefoneOpcao2() {
		return telefoneOpcao2;
	}

	public void setTelefoneOpcao2(String telefoneOpcao2) {
		this.telefoneOpcao2 = telefoneOpcao2;
	}

	public Integer getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
}
