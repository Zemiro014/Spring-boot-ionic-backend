package com.jeronimokulandissa.cursomc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {
	
	/* Teste */
	//@Autowired
	//private S3Service s3Service;
	/*
	 * O "CommandLineRunner" permite executar alguma ação assim que a 
	 * aplicação for iniciada
	 * */

	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception 
	{
		// FOi usado para testar o envio do primeiro arquivo ao S# da AWS:  s3Service.uploadFile("C:\\temp\\ws\\images\\all_star.jpeg");
		//s3Service.uploadFile("C:\\temp\\ws\\images\\poderoso.jpg");
	}

}
                         