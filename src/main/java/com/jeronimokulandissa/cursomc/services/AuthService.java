package com.jeronimokulandissa.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.repositories.ClienteRepository;
import com.jeronimokulandissa.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService 
{
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) 
	{
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) 
		{
			throw new ObjectNotFoundException("Este email não consta no sistema");
		}
		
		String newPass = newPassword();
		cliente.setSenha(bCryptPasswordEncoder.encode(newPass));
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente,newPass);
	}

	private String newPassword() 
	{
		// gerador de nova senha
		
		char[] vet = new char[10];
		for(int i = 0; i < 10; i++)
		{
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		// TODO Auto-generated method stub
		int opt = rand.nextInt(3);
		if(opt == 0) // gera um digito
		{
			return (char) (rand.nextInt(10) + 48);
		}
		else if(opt == 1) // gera letra maiúsculo
		{
			return (char) (rand.nextInt(26) + 65);
		}
		else // gera letra minúscula
		{
			return (char) (rand.nextInt(56) + 97);
		}
	}
	
	
}
