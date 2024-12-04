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

import com.generation.gamestore.dto.CategoriaCreateDTO;
import com.generation.gamestore.dto.CategoriaUpdateDTO;
import com.generation.gamestore.model.Categoria;
import com.generation.gamestore.repository.CategoriaRepository;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Categoria", description = "Operações relacionadas as categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok(categoriaRepository.findAll());

	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		return categoriaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping(value = "/categorias/{categoria}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Categoria>> getByProduto(@PathVariable String categoria){
		return ResponseEntity.ok(categoriaRepository.findByCategoriaContainingIgnoreCase(categoria));
	}
	
	@PostMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Categoria> post(@Valid @RequestBody CategoriaCreateDTO categoriaDTO){
		
		Categoria categoria = new Categoria();
		categoria.setCategoria(categoriaDTO.getCategoria());
		categoria.setDescricao(categoriaDTO.getDescricao());		
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriaRepository.save(categoria));
	}
	
	@PutMapping(
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE
	    ) 
	public ResponseEntity<Categoria> put(@Valid @RequestBody CategoriaUpdateDTO categoriaDTO){
		return categoriaRepository.findById(categoriaDTO.getId())
				.map(categoria -> {
					
					categoria.setCategoria(categoriaDTO.getCategoria());
					categoria.setDescricao(categoriaDTO.getDescricao());
					
					return ResponseEntity.status(HttpStatus.OK)
							.body(categoriaRepository.save(categoria));
					
				}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());	
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		
		if(categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		categoriaRepository.deleteById(id);
		
	}
	

}
