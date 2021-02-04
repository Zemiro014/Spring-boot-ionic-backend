package com.jeronimokulandissa.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jeronimokulandissa.cursomc.domain.Categoria;
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
	@Transactional(readOnly=true) // Informa que é apenas uma consulta
	@Query("SELECT DISTINCT obj FROM Producto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias ") 
	Page<Producto> search(@Param("nome") String nome, @Param("categorias") List<Categoria>categorias, Pageable pageRequest); // Método que realiza uma consulta customizado JPQL
	
	@Transactional(readOnly=true) // Informa que é apenas uma consulta
	Page<Producto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria>categorias, Pageable pageRequest); // Esse método pode substituir o método acima. São equivalentes
}
