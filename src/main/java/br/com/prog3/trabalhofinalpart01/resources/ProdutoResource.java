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

import br.com.prog3.trabalhofinalpart01.domain.Produto;
import br.com.prog3.trabalhofinalpart01.service.ProdutoService;

@RestController
@RequestMapping("/api/v1/produtos")

public class ProdutoResource {

	@Autowired
	private ProdutoService produtoService;

	@PostMapping
	public Produto save(@RequestBody Produto produto) {
		return produtoService.save(produto);
	}

	@GetMapping
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> produto = produtoService.findAll();
		if (produto == null || produto.isEmpty()) {
			return new ResponseEntity<List<Produto>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Produto>>(produto, HttpStatus.OK);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> findById(@PathVariable Long id) {
		return produtoService.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Produto> update(@PathVariable("id") Long id, @RequestBody Produto produto) {
		return produtoService.findById(id).map(record -> {

			record.setNome(produto.getNome());
			record.setDescricao(produto.getDescricao());
			record.setPrecoCompra(produto.getPrecoCompra());
			record.setPrecoVenda(produto.getPrecoVenda());
			record.setDesconto(produto.getDesconto());
			record.setImagem(produto.getImagem());
			record.setGarantia(produto.getGarantia());
			record.setFabricante(produto.getFabricante());

			Produto updated = produtoService.save(record);
			return ResponseEntity.ok().body(updated);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = { "/{id}" })
	public ResponseEntity<?> delete(@PathVariable Long id) {
		return produtoService.findById(id).map(record -> {
			produtoService.deleteById(id);
			return ResponseEntity.ok().build();
		}).orElse(ResponseEntity.notFound().build());
	}

}
