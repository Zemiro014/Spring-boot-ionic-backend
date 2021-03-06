package com.jeronimokulandissa.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends AbstractEmailService
{
	// Sempre que for chamado a class "MockEmailService", terá apenas um LOG. Por isso ele é static
	private static final Logger LOG = LoggerFactory.getLogger(MockEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) 
	{
		LOG.info("Simulando envio de email ...");
		LOG.info(msg.toString());
		LOG.info("Email enviado");
	}

	@Override
	public void sendHtmlEmail(MimeMessage mm) 
	{
		LOG.info("Simulando envio de email HTML ...");
		LOG.info(mm.toString());
		LOG.info("Email enviado");
	}

}
