package com.generation.gamestore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class ProdutoCategoriaDTO {

	@NotNull(message = "O Atributo Id é obrigatório")
	@Schema(description = "Id da categoria", example = "1")
	private Long id;

	public Long getId() {
		return id;
	}
	
}
