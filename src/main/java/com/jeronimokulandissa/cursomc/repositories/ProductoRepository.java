package com.jeronimokulandissa.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jeronimokulandissa.cursomc.domain.Producto;

/* Esta interface permite criar objectos da camada de acesso de banco de dados
 * referente ao tipo "Producto" da camada domain*/
@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Integer>
{
	/*JpaRepository<Producto, Integer> é uma interface que permite acessar os dados
	 * com base ao tipo que lhe foi passado "Producto" com o atributo 
	 * idetnificador "Integer" que foi definido na class "Producto" no domínio
	 * 
	 * Todo objecto do tipo ProductoRepository, está mapeado com a tabela "Producto"
	 * no banco de dados e é capaz de realizar operações nesta tabela
	 * */	
}
