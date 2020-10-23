package br.com.prog3.trabalhofinalpart01.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.prog3.trabalhofinalpart01.domain.Venda;
import br.com.prog3.trabalhofinalpart01.repository.VendaRepository;

@Service
public class VendaService {
	@Autowired
	private VendaRepository vendaRepository;

	public Venda save(Venda venda) {
		return vendaRepository.save(venda);
	}

	public List<Venda> findAll() {
		return (List<Venda>) vendaRepository.findAll();
	}

	public Optional<Venda> findById(Long id) {
		return vendaRepository.findById(id);
	}

	public Venda update(Venda venda) {
		return vendaRepository.save(venda);
	}
	
	public void deleteById(Long id) {
		vendaRepository.deleteById(id);
	}

}
