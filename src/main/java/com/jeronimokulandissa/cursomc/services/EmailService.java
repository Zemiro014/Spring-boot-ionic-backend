package com.jeronimokulandissa.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.jeronimokulandissa.cursomc.domain.Pedido;

public interface EmailService 
{
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
