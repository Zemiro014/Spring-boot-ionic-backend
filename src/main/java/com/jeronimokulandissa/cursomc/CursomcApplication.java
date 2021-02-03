package com.jeronimokulandissa.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeronimokulandissa.cursomc.domain.Categoria;
import com.jeronimokulandissa.cursomc.domain.Cidade;
import com.jeronimokulandissa.cursomc.domain.Estado;
import com.jeronimokulandissa.cursomc.domain.Producto;
import com.jeronimokulandissa.cursomc.repositories.CategoriaRepository;
import com.jeronimokulandissa.cursomc.repositories.CidadeRepository;
import com.jeronimokulandissa.cursomc.repositories.EstadoRepository;
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		/*Este método auxiliar é usado para inserir os objectos ao banco de 
		 * dados assim que a aplicação é iniciada*/
		
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
	}

}
                         