package com.jeronimokulandissa.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jeronimokulandissa.cursomc.services.DBService;

// Configuração específicas para application-test.propertirs
@Configuration
@Profile("dev")
public class DevConfig 
{
	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String startegy;
	
	@Bean
	public boolean instatiatedatabase() throws ParseException 
	{
		if(!"create".equals(startegy)) 
		{
			return false;
		}
		dbService.instantiateTestDatabase();
		return true;
	}
}
