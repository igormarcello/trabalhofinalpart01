package br.com.prog3.trabalhofinalpart01.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.prog3.trabalhofinalpart01.domain.Produto;

@Repository

public interface ProdutoRepository extends CrudRepository<Produto, Long> {
	
	//List<Produto> findByNome(Nome nome);

}
