package com.jeronimokulandissa.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.jeronimokulandissa.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService
{
	@Value("${default.sender}") // Pega o valor do "default.sender", a partir do arquivo "Aplivcation.properties"
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido obj) 
	{
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(obj);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) 
	{
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Pedido cinfirmado! Código: "+ obj.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
}
