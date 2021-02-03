package com.jeronimokulandissa.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.domain.Cidade;
import com.jeronimokulandissa.cursomc.domain.Cliente;
import com.jeronimokulandissa.cursomc.domain.Endereco;
import com.jeronimokulandissa.cursomc.domain.Estado;
import com.jeronimokulandissa.cursomc.domain.Pagamento;
import com.jeronimokulandissa.cursomc.domain.PagamentoComBoleto;
import com.jeronimokulandissa.cursomc.domain.PagamentoComCartao;
import com.jeronimokulandissa.cursomc.domain.Pedido;
import com.jeronimokulandissa.cursomc.domain.Producto;
import com.jeronimokulandissa.cursomc.domain.enums.EstadoPagamento;
import com.jeronimokulandissa.cursomc.domain.enums.TipoCliente;
import com.jeronimokulandissa.cursomc.repositories.CategoriaRepository;
import com.jeronimokulandissa.cursomc.repositories.CidadeRepository;
import com.jeronimokulandissa.cursomc.repositories.ClienteRepository;
import com.jeronimokulandissa.cursomc.repositories.EnderecoRepository;
import com.jeronimokulandissa.cursomc.repositories.EstadoRepository;
import com.jeronimokulandissa.cursomc.repositories.PagamentoRepository;
import com.jeronimokulandissa.cursomc.repositories.PedidoRepository;
import com.jeronimokulandissa.cursomc.repositories.ProductoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	/*
	 * O "CommandLineRunner" permite executar alguma ação assim que a 
	 * aplicação for iniciada
	 * */
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
	private PagamentoRepository pagamentooRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		/*Este método auxiliar é usado para inserir os objectos ao banco de 
		 * dados assim que a aplicação é iniciada
		 * 
		 * Estamos usando ele para povoar o nosso banco de dados
		 * */
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Producto p1 = new Producto(null, "Computador",2000.00);
		Producto p2 = new Producto(null, "Impressora",800.00);
		Producto p3 = new Producto(null, "Mouser",80.00);
		
		/*
		 * Fazendo a associação das categorias com os seus respectivos productos
		 * 
		 * As categorias "cat1 e cat2", conhecem os productos associados a elas
		 * */
		cat1.getProductos().addAll(Arrays.asList(p1,p2,p3)); // O "cat1" está associado com "p1, p2 e p3"
		cat2.getProductos().addAll(Arrays.asList(p2)); // O "cat2" está associado com "p2"
		
		/*
		 * Os Productos "p1, p2, p3", conhecem as categorias associadas a elas
		 * */
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		productoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
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
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA );
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		// Sempre salve primeiro o objecto que não possui dependência
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2020 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2020 19:35"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentooRepository.saveAll(Arrays.asList(pagto1, pagto2));
	}

}
                         