package com.jeronimokulandissa.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jeronimokulandissa.cursomc.services.DBService;
import com.jeronimokulandissa.cursomc.services.EmailService;
import com.jeronimokulandissa.cursomc.services.MockEmailService;

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
	
	@Bean // Todo método que é declarado como Bean, se transforma em uma propriedade do projecto que o sistema irá usalo para injectar alguma dependência conveniente
	public EmailService emailService() 
	{
		return new MockEmailService();
	}
}
