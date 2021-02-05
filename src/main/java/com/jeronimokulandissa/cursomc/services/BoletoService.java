package com.jeronimokulandissa.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService 
{
	
	// Este método adiciona 7 dias na data atual
	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido)
	{
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(instanteDoPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		
		pagto.setDataPagamento(cal.getTime());
	}
}
