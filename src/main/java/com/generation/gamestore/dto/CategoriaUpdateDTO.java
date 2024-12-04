package com.generation.gamestore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CategoriaUpdateDTO {
	
	@NotNull(message = "O atributo ID é obrigatório")
	@Schema(description = "Id da categoria", example = "1")
	private Long id;

	@NotNull(message = "A categoria deve ser nomeada")
	@Schema(description = "Nome da categoria", example = "RPG")
	private String categoria;
	
	@Schema(description = "Descrição da categoria", example = "Aqui você encontra uma seleção incrível de jogos de interpretação de personagens, onde a imaginação ganha vida. Explore mundos fantásticos, crie heróis únicos e embarque em aventuras épicas com seus amigos.")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
