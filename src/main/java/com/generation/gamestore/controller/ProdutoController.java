package com.generation.gamestore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.gamestore.dto.ProdutoCreateDTO;
import com.generation.gamestore.dto.ProdutoUpdateDTO;
import com.generation.gamestore.model.Categoria;
import com.generation.gamestore.model.Produto;
import com.generation.gamestore.repository.ProdutoRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Produtos", description = "Operações relacionadas aos produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Produto>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());

	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Produto> getById(@PathVariable Long id) {
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping(value = "/produto/{produto}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Produto>> getByProduto(@PathVariable String produto){
		return ResponseEntity.ok(produtoRepository.findByProdutoContainingIgnoreCase(produto));
	}
	
	@PostMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Produto> post(@Valid @RequestBody ProdutoCreateDTO produtoDTO){
		
		Produto produto = new Produto();
		produto.setProduto(produtoDTO.getProduto());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setAnoDeLancamento(produtoDTO.getAnoDeLancamento());
		produto.setPreco(produtoDTO.getPreco());
		
		Categoria categoria = new Categoria();
		categoria.setId(produtoDTO.getCategoria().getId());
		
		produto.setCategoria(categoria);
		
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(produtoRepository.save(produto));
	}
	
	@PutMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Produto> put(@Valid @RequestBody ProdutoUpdateDTO produtoDTO){
		return produtoRepository.findById(produtoDTO.getId())
				.map(produto -> {
					
					produto.setProduto(produtoDTO.getProduto());
					produto.setDescricao(produtoDTO.getDescricao());
					produto.setAnoDeLancamento(produtoDTO.getAnoDeLancamento());
					produto.setPreco(produtoDTO.getPreco());
					
					Categoria categoria = new Categoria();
					categoria.setId(produtoDTO.getCategoria().getId());
					
					produto.setCategoria(categoria);
					
					return ResponseEntity.status(HttpStatus.OK)
							.body(produtoRepository.save(produto));
					
				}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());	
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produto> pessoa = produtoRepository.findById(id);
		
		if(pessoa.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		produtoRepository.deleteById(id);
		
	}
	
	
	
}
