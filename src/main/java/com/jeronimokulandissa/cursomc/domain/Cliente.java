package com.jeronimokulandissa.cursomc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jeronimokulandissa.cursomc.domain.enums.TipoCliente;

@Entity
public class Cliente implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String email;
	private String cpfOuCnpj;
	/*
	 * private TipoCliente tipo  -> foi substituida por "private Integer tipo"
	 *  Isso se deve ao facto de que, para Class "Client" (mund interno) o tipo será exposto como tipo "Integer"
	 *  mas para o mundo externo será exposto como tipo "TipoCliente"
	 * */ 
	private Integer tipo;
	
	// Associação entre Clinte e endereço: Um "Cliente" possui vários "Endereco" (uma lista de "Endereco")
	// @JsonManagedReference 	// Está sendo informado que o Cliente pode Serializar os seus "enderecos" (É feito quando duas classes conhecem um ao outro)
	// O "cascade = CascadeType.ALL" permite que toda alteração (delete, update) realizada em um Cliente, afetará automáticamente os Enderecos deste Cliente
	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
	private List<Endereco> enderecos = new ArrayList<>();
	
	
	/*
	 * Como a Class telefone é simples demais, optou-se por não implementa-lo.
	 * Ela foi representado dentro da Class Client como um conjunto (Set<>)
	 * O Set<> é uma interface de coleção que não aceita repitições. Ela pode ser implementado pelo HashSet
	 * 
	 * 
	 * O "@ElementCollection" permite a JPA cria a coleção telefones como uma entidade fraca no banco de dados
	 * O "@CollectionTable" permite a JPA atribuir um nome à tabela auxiliar de telefones
	 * */ 
	@ElementCollection
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	// @JsonBackReference trocado por @JsonIgnore 
	@JsonIgnore
	@OneToMany(mappedBy="cliente") // Declarando o tipo de associação 1 para muitos, entre o "Cliente" e o "Pedido"
	private List<Pedido> pedidos = new ArrayList<>();
	
	public Cliente() {}

	public Cliente(Integer id, String nome, String email, String cpfOuCnpj, TipoCliente tipo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = ( tipo==null) ? null : tipo.getCod();
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

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	/*
	 * Fezemos com que a Class "Cliente" armazene internamente o "tipo" como sendo "Integer" 
	 * mas este "tipo" será exposta ao sistema como "TipoCliente"
	 * */
	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

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
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}
