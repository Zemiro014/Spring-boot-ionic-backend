package com.jeronimokulandissa.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.dto.ClienteDTO;
import com.jeronimokulandissa.cursomc.repositories.ClienteRepository;
import com.jeronimokulandissa.cursomc.resources.exception.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request; // Esse carinha ai me permite obter o parâmetro (2) da URI "http://localhost:8080/clientes/2"
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE); // É deste jeito que consigo pegar o -2- da URI "http://localhost:8080/clientes/2"
		Integer uriId = Integer.parseInt(map.get("id"));
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(aux != null && aux.getId().equals(uriId)) 
		{
			list.add(new FieldMessage("Email","O email inserido já existe"));
		}
		// inclua os testes aqui, inserindo erros na lista
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}