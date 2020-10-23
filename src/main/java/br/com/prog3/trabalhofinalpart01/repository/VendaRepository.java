package br.com.prog3.trabalhofinalpart01.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.prog3.trabalhofinalpart01.domain.Venda;

@Repository
public interface VendaRepository extends CrudRepository<Venda, Long>{

}
