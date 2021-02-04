package com.jeronimokulandissa.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.domain.enums.TipoCliente;
import com.jeronimokulandissa.cursomc.dto.ClienteNewDTO;
import com.jeronimokulandissa.cursomc.repositories.ClienteRepository;
import com.jeronimokulandissa.cursomc.resources.exception.FieldMessage;
import com.jeronimokulandissa.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		if(objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj()))
		{
			list.add(new FieldMessage("CpfOuCnpj", "CPF inválido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj()))
		{
			list.add(new FieldMessage("CpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(aux != null) 
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