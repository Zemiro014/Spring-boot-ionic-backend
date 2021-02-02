package com.jeronimokulandissa.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeronimokulandissa.cursomc.domain.Categoria;

/* Esta interface permite criar objectos da camada de acesso de banco de dados
 * referente ao tipo "Categoria" da camada domain*/
@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Integer>
{
	/*JpaRepository<Categoria, Integer> é uma interface que permite acessar os dados
	 * com base ao tipo que lhe foi passado "Categoria" com o atributo 
	 * idetnificador "Integer" que foi definido na class do domínio
	 * 
	 * Todo objecto do tipo CategoriaRepository, está mapeado com a tabela "Categoria"
	 * no banco de dados e é capaz de realizar operações nesta tabela
	 * */	
}
