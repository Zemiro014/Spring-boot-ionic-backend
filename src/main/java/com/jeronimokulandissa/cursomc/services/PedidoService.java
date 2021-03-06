package com.jeronimokulandissa.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.domain.ItemPedido;
import com.jeronimokulandissa.cursomc.domain.PagamentoComBoleto;
import com.jeronimokulandissa.cursomc.domain.Pedido;
import com.jeronimokulandissa.cursomc.domain.enums.EstadoPagamento;
import com.jeronimokulandissa.cursomc.repositories.ItemPedidoRepository;
import com.jeronimokulandissa.cursomc.repositories.PagamentoRepository;
import com.jeronimokulandissa.cursomc.repositories.PedidoRepository;
import com.jeronimokulandissa.cursomc.security.UserSS;
import com.jeronimokulandissa.cursomc.services.exceptions.AuthorizationException;
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
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
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
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
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
			ip.setProducto(productoService.find(ip.getProducto().getId()));
			ip.setPreco(ip.getProducto().getPreco());
			ip.setPedido(obj);
		}
		itemPedidoRepository.saveAll(obj.getItens());
		//System.out.println(obj); // Printa automaticamente o toString() do objecto
		//emailService.sendOrderConfirmationEmail(obj);
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy,  String direction)
	{
		// Obter o usuário logado
		UserSS user = UserService.authenticated();
		
		if(user == null) 
		{
			throw new AuthorizationException("Acesso não permitido");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy );
		Cliente cliente = clienteService.find(user.getId());
		return repo.findByCliente(cliente, pageRequest);
	}
}
