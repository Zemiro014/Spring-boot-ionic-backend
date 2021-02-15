package com.jeronimokulandissa.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.domain.Cidade;
import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.domain.Endereco;
import com.jeronimokulandissa.cursomc.domain.Estado;
import com.jeronimokulandissa.cursomc.domain.ItemPedido;
import com.jeronimokulandissa.cursomc.domain.Pagamento;
import com.jeronimokulandissa.cursomc.domain.PagamentoComBoleto;
import com.jeronimokulandissa.cursomc.domain.PagamentoComCartao;
import com.jeronimokulandissa.cursomc.domain.Pedido;
import com.jeronimokulandissa.cursomc.domain.Producto;
import com.jeronimokulandissa.cursomc.domain.enums.EstadoPagamento;
import com.jeronimokulandissa.cursomc.domain.enums.Perfil;
import com.jeronimokulandissa.cursomc.domain.enums.TipoCliente;
import com.jeronimokulandissa.cursomc.repositories.CategoriaRepository;
import com.jeronimokulandissa.cursomc.repositories.CidadeRepository;
import com.jeronimokulandissa.cursomc.repositories.ClienteRepository;
import com.jeronimokulandissa.cursomc.repositories.EnderecoRepository;
import com.jeronimokulandissa.cursomc.repositories.EstadoRepository;
import com.jeronimokulandissa.cursomc.repositories.ItemPedidoRepository;
import com.jeronimokulandissa.cursomc.repositories.PagamentoRepository;
import com.jeronimokulandissa.cursomc.repositories.PedidoRepository;
import com.jeronimokulandissa.cursomc.repositories.ProductoRepository;

@Service
public class DBService 
{
	
	@Autowired
	private CategoriaRepository categoriaRepository;	
	@Autowired
	private ProductoRepository productoRepository;	
	@Autowired 
	private EstadoRepository estadoRepository;	
	@Autowired
	private CidadeRepository cidadeRerpository;	
	@Autowired
	private ClienteRepository clienteRepository;	
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	public void instantiateTestDatabase() throws ParseException 
	{
		/*Este método auxiliar é usado para inserir os objectos ao banco de 
		 * dados assim que a aplicação é iniciada
		 * 
		 * Estamos usando ele para povoar o nosso banco de dados
		 * */
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e banho");
		Categoria cat4 = new Categoria(null, "Eletrónicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Producto p1 = new Producto(null, "Computador",2000.00);
		Producto p2 = new Producto(null, "Impressora",800.00);
		Producto p3 = new Producto(null, "Mouser",80.00);
		Producto p4 = new Producto(null, "Mesa de escritório",300.00);
		Producto p5 = new Producto(null, "Toalha",50.00);
		Producto p6 = new Producto(null, "COlcha",200.00);
		Producto p7 = new Producto(null, "TV true color",1200.00);
		Producto p8 = new Producto(null, "Roçadeira",800.00);
		Producto p9 = new Producto(null, "Abajour",100.00);
		Producto p10 = new Producto(null, "Pendente",180.00);
		Producto p11 = new Producto(null, "Shampoor",90.00);
		
		/* Linhas adicionados para povoar*/
		
		Producto p12 = new Producto(null, "Producto 12", 10.00);
		Producto p13 = new Producto(null, "Producto 13", 10.00);
		Producto p14 = new Producto(null, "Producto 14", 10.00);
		Producto p15 = new Producto(null, "Producto 15", 10.00);
		Producto p16 = new Producto(null, "Producto 16", 10.00);
		Producto p17 = new Producto(null, "Producto 17", 10.00);
		Producto p18 = new Producto(null, "Producto 18", 10.00);
		Producto p19 = new Producto(null, "Producto 19", 10.00);
		Producto p20 = new Producto(null, "Producto 20", 10.00);
		Producto p21 = new Producto(null, "Producto 21", 10.00);
		Producto p22 = new Producto(null, "Producto 22", 10.00);
		Producto p23 = new Producto(null, "Producto 23", 10.00);
		Producto p24 = new Producto(null, "Producto 24", 10.00);
		Producto p25 = new Producto(null, "Producto 25", 10.00);
		Producto p26 = new Producto(null, "Producto 26", 10.00);
		Producto p27 = new Producto(null, "Producto 27", 10.00);
		Producto p28 = new Producto(null, "Producto 28", 10.00);
		Producto p29 = new Producto(null, "Producto 29", 10.00);
		Producto p30 = new Producto(null, "Producto 30", 10.00);
		Producto p31 = new Producto(null, "Producto 31", 10.00);
		Producto p32 = new Producto(null, "Producto 32", 10.00);
		Producto p33 = new Producto(null, "Producto 33", 10.00);
		Producto p34 = new Producto(null, "Producto 34", 10.00);
		Producto p35 = new Producto(null, "Producto 35", 10.00);
		Producto p36 = new Producto(null, "Producto 36", 10.00);
		Producto p37 = new Producto(null, "Producto 37", 10.00);
		Producto p38 = new Producto(null, "Producto 38", 10.00);
		Producto p39 = new Producto(null, "Producto 39", 10.00);
		Producto p40 = new Producto(null, "Producto 40", 10.00);
		Producto p41 = new Producto(null, "Producto 41", 10.00);
		Producto p42 = new Producto(null, "Producto 42", 10.00);
		Producto p43 = new Producto(null, "Producto 43", 10.00);
		Producto p44 = new Producto(null, "Producto 44", 10.00);
		Producto p45 = new Producto(null, "Producto 45", 10.00);
		Producto p46 = new Producto(null, "Producto 46", 10.00);
		Producto p47 = new Producto(null, "Producto 47", 10.00);
		Producto p48 = new Producto(null, "Producto 48", 10.00);
		Producto p49 = new Producto(null, "Producto 49", 10.00);
		Producto p50 = new Producto(null, "Producto 50", 10.00);
		
		cat1.getProductos().addAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
		p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
		p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		p12.getCategorias().add(cat1);
		p13.getCategorias().add(cat1);
		p14.getCategorias().add(cat1);
		p15.getCategorias().add(cat1);
		p16.getCategorias().add(cat1);
		p17.getCategorias().add(cat1);
		p18.getCategorias().add(cat1);
		p19.getCategorias().add(cat1);
		p20.getCategorias().add(cat1);
		p21.getCategorias().add(cat1);
		p22.getCategorias().add(cat1);
		p23.getCategorias().add(cat1);
		p24.getCategorias().add(cat1);
		p25.getCategorias().add(cat1);
		p26.getCategorias().add(cat1);
		p27.getCategorias().add(cat1);
		p28.getCategorias().add(cat1);
		p29.getCategorias().add(cat1);
		p30.getCategorias().add(cat1);
		p31.getCategorias().add(cat1);
		p32.getCategorias().add(cat1);
		p33.getCategorias().add(cat1);
		p34.getCategorias().add(cat1);
		p35.getCategorias().add(cat1);
		p36.getCategorias().add(cat1);
		p37.getCategorias().add(cat1);
		p38.getCategorias().add(cat1);
		p39.getCategorias().add(cat1);
		p40.getCategorias().add(cat1);
		p41.getCategorias().add(cat1);
		p42.getCategorias().add(cat1);
		p43.getCategorias().add(cat1);
		p44.getCategorias().add(cat1);
		p45.getCategorias().add(cat1);
		p46.getCategorias().add(cat1);
		p47.getCategorias().add(cat1);
		p48.getCategorias().add(cat1);
		p49.getCategorias().add(cat1);
		p50.getCategorias().add(cat1);
		/*Fim da adição*/
		
		/*
		 * Fazendo a associação das categorias com os seus respectivos productos
		 * 
		 * As categorias "cat1 e cat2", conhecem os productos associados a elas
		 * */
		cat1.getProductos().addAll(Arrays.asList(p1,p2,p3)); // O "cat1" está associado com "p1, p2 e p3"
		cat2.getProductos().addAll(Arrays.asList(p2, p4)); // O "cat2" está associado com "p2"
		cat3.getProductos().addAll(Arrays.asList(p5, p6));
		cat4.getProductos().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProductos().addAll(Arrays.asList(p8));
		cat6.getProductos().addAll(Arrays.asList(p9, p10));
		cat7.getProductos().addAll(Arrays.asList(p11));
		
		/*
		 * Os Productos "p1, p2, p3", conhecem as categorias associadas a elas
		 * */
		p1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		p4.getCategorias().addAll(Arrays.asList(cat2));
		p5.getCategorias().addAll(Arrays.asList(cat3));
		p6.getCategorias().addAll(Arrays.asList(cat3));
		p7.getCategorias().addAll(Arrays.asList(cat4));
		p8.getCategorias().addAll(Arrays.asList(cat5));
		p9.getCategorias().addAll(Arrays.asList(cat6));
		p10.getCategorias().addAll(Arrays.asList(cat6));
		p11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2, cat3, cat4, cat5, cat6, cat7));
		productoRepository.saveAll(Arrays.asList(p1,p2,p3, p4, p5, p6, p7, p8, p9, p10, p11));
		
		productoRepository.saveAll(Arrays.asList(p12, p13, p14, p15, p16, p17, p18, p19, p20,
				p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p34, p35, p36, p37, p38,
				p39, p40, p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		// Quando a associação é 1 para 1; associamos dentro do construtor
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo",est2);
		Cidade c3 = new Cidade(null, "Campinas",est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRerpository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, passEncoder.encode("123") );
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Cliente cli2 = new Cliente(null, "Iza Santos", "cursoszemiro04@gmail.com", "96059819044", TipoCliente.PESSOAFISICA, passEncoder.encode("123") );
		cli2.addPerfil(Perfil.ADMIN);
		cli2.getTelefones().addAll(Arrays.asList("478124578","987451247"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);		
		Endereco e3 = new Endereco(null, "Avenida Floriano", "2106", null, "Centro", "938001000", cli2, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		// Sempre salve primeiro o objecto que não possui dependência
		clienteRepository.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepository.saveAll(Arrays.asList(e1, e2, e3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2020 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
}
