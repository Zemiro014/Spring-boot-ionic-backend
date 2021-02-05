package com.jeronimokulandissa.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jeronimokulandissa.cursomc.services.DBService;

// Configuração específicas para application-test.propertirs
@Configuration
@Profile("test")
public class TestConfig 
{
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instatiatedatabase() throws ParseException 
	{
		dbService.instantiateTestDatabase();
		return true;
	}
}
