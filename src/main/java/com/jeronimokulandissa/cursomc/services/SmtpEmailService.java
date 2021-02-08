package com.jeronimokulandissa.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService
{
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	// Sempre que for chamado a class "MockEmailService", terá apenas um LOG. Por isso ele é static
		private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
		
	@Override
	public void sendEmail(SimpleMailMessage msg) 
	{
		LOG.info("Enviando email ...");
		mailSender.send(msg);
		//LOG.info(msg.toString());
		LOG.info("Email enviado");		
	}

	@Override
	public void sendHtmlEmail(MimeMessage mm) {
		LOG.info("Enviando email ...");
		javaMailSender.send(mm);
		//LOG.info(msg.toString());
		LOG.info("Email enviado");		
	}

}
