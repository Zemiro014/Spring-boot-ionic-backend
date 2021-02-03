package com.jeronimokulandissa.cursomc.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.jeronimokulandissa.cursomc.domain.enums.EstadoPagamento;

/*
 * Essa Class é uma Super Class. Ela póssui Sub Classes
 * 
 * Para fazer a associação de "Herança", basta colocar o "@Inheritance" na -Super Class- e o "@Entity" nas suas -Sub Classes-
 * 
 * O "strategy = InheritanceType.JOINED" é tipo de estratégia escolhida para gerar a tabela no banco de dados
 * Vai gerar apenas uma tabela contendo os dados de todas as SubClasses desta SuperClass
 * 
 * Essa Class é "Obstract" de modo a garantir que não se pode instanciar um objecto do tipo "Pagamento"
 * Para Instanciar ele, é preciso declarar "Pagamento nome = new nomeDaSubClassDela"
 * */ 
@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public abstract class Pagamento implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	/*Como a associação entre Pedido e pagamento é um para um, logo o Id do Pagamento é o mesmo que o Id do Pedidio
	 * 
	 * Logo não precisa do "@GeneratedValue(strategy=GenerationType.IDENTITY)"
	 * mas precisar declarar na associação o "@OneToOne"
	 * */ 
	@Id
	private Integer id; 
	// private EstadoPagamento estado; -> foi suibstituido pelo mesmo motivo do TipoCliente
	private Integer estado;
	
	@JsonBackReference
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId // É usado para garantir que os Ids sejam os mesmos.
	private Pedido pedido;
	
	public Pagamento() {}

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) 
	{
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public void setEstado(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
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
		Pagamento other = (Pagamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
