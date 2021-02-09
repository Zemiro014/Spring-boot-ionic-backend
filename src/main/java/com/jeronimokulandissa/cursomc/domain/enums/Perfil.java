package com.jeronimokulandissa.cursomc.domain.enums;

public enum Perfil 
{
	ADMIN(1, "ROLE_ADMIN"), // "ROLE_ADMIN" é uma exigência do Spring Security
	CLIENTE(2, "ROLE_CLIENTE");
	
	private int cod;
	private String descricao;
	
	// Construtores do tipo Enum devem ser private
	private Perfil(int cod, String descricao) 
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
	public static Perfil toEnum(Integer cod) 
	{
		if(cod==null) {
		return null;
		}
		
		for(Perfil x : Perfil.values()) 
		{
			if(cod.equals(x.getCod())) 
			{
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: "+cod);
	}
}
