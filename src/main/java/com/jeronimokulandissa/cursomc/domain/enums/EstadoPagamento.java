package com.jeronimokulandissa.cursomc.domain.enums;

public enum EstadoPagamento 
{
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");
	
	private int cod;
	private String descricao;
	
	// Construtores do tipo Enum devem ser private
	private EstadoPagamento(int cod, String descricao) 
	{
		this.cod=cod;
		this.descricao = descricao;
	}
	
	public int getCod() 
	{
		return cod;
	}
	
	public String getDescricao() 
	{
		return descricao;
	}
	
	// Todo método estatic é possível de ser executado (rodado) mesmo sem instanciar objectos da class
	public static EstadoPagamento toEnum(Integer cod) 
	{
		if(cod==null) {
		return null;
		}
		
		for(EstadoPagamento x : EstadoPagamento.values()) 
		{
			if(cod.equals(x.getCod())) 
			{
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
