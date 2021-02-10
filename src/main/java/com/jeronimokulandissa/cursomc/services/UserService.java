package com.jeronimokulandissa.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.jeronimokulandissa.cursomc.security.UserSS;

public class UserService 
{
	
	/*
	 * Este método poderá ser chamado independentemente de se instanciar a class UserService. Por isso que é estático
	 * 
	 * Esse método vai retornar o usuário autenticado
	 * */ 
	public static UserSS authenticated() 
	{
		try 
		{
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) 
		{
			return null;
		}
	}
}
