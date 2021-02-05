package com.jeronimokulandissa.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeronimokulandissa.cursomc.domain.ItemPedido;
import com.jeronimokulandissa.cursomc.domain.PagamentoComBoleto;
import com.jeronimokulandissa.cursomc.domain.Pedido;
import com.jeronimokulandissa.cursomc.domain.enums.EstadoPagamento;
import com.jeronimokulandissa.cursomc.repositories.ItemPedidoRepository;
import com.jeronimokulandissa.cursomc.repositories.PagamentoRepository;
import com.jeronimokulandissa.cursomc.repositories.PedidoRepository;
import com.jeronimokulandissa.cursomc.services.exceptions.ObjectNotFoundException;


@Service
public class PedidoService 
{
	
	/*Quando se declara uma dependência dentro de uma class e se coloca "Autowired"
	 * essa dependência será automáticamente instanciada pelo Spring usando (ID ou IC)
	 * */
	@Autowired 
	private PedidoRepository repo;
	
	@Autowired 
	private BoletoService boletoService;
	
	@Autowired 
	private PagamentoRepository pagamentoRepository;
	
	@Autowired 
	private ProductoService productoService;
	
	@Autowired 
	private ItemPedidoRepository itemPedidoRepository;
	
	public Pedido find(Integer id) 
	{
		Optional<Pedido> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
	
	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido ip : obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPreco(productoService.find(ip.getProducto().getId()).getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
