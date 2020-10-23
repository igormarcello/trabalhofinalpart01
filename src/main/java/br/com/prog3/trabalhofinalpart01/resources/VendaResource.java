package br.com.prog3.trabalhofinalpart01.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.prog3.trabalhofinalpart01.domain.Venda;
import br.com.prog3.trabalhofinalpart01.service.VendaService;

@RestController
@RequestMapping("/api/v1/vendas")

public class VendaResource {
	@Autowired
	private VendaService vendaService;

	@GetMapping
	public ResponseEntity<List<Venda>> findAll() {
		List<Venda> vendas = vendaService.findAll();
		if (vendas == null || vendas.isEmpty()) {
			return new ResponseEntity<List<Venda>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Venda>>(vendas, HttpStatus.OK);
	}

	@PostMapping
	public Venda create(@RequestBody Venda venda) {
		return vendaService.save(venda);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return vendaService.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Venda> update(@PathVariable("id") Long id, @RequestBody Venda venda) {
		return vendaService.findById(id).map(record -> {

			record.setValor(venda.getValor());
			record.setData(venda.getData());
			record.setDesconto(venda.getDesconto());

			Venda updated = vendaService.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return vendaService.findById(id).map(record -> {
			vendaService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
