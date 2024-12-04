package com.generation.gamestore.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ProdutoCreateDTO {
	
	@NotNull(message = "O Atributo Nome é obrigatório")
	@Schema(description = "Nome do produto", example = "Final Fantasy XVI")
	private String produto;

	@NotNull(message = "O atributo descrição é obrigatório")
	@Size(min = 10, max = 5000, message = "O atributo deve conter no mínimo 10 e no máximo 5000 caracteres")
	@Schema(description = "Descrição do produto", example = "Final Fantasy XVI leva os jogadores a Valisthea, um mundo em crise devido à escassez de éter. Acompanhamos Clive Rosfield, que busca vingança após uma tragédia familiar, enfrentando Eikons e inimigos em combates dinâmicos e emocionantes. A narrativa rica e personagens complexos tornam esta jornada inesquecível")
	private String descricao;
	
	@NotNull(message = "O preço deve ser registrado")
	@Digits(integer = 4, fraction = 2, message = "O preço esperado deve conter até 4 digitos inteiros com 2 casas decimais")
	@Schema(description = "Preço do produto", example = "249.50")
	private BigDecimal preco;
	
	@JsonFormat(pattern = "yyyy")
	@Pattern(regexp = "^\\d{4}$", message = "O ano deve ser um número de quatro dígitos")
	@Min(1952)
	@Max(2200)
	@Schema(description = "Ano de lançamento do produto", example = "2024")
	private String anoDeLancamento;
	
	@JsonIgnoreProperties("categoria")
	private ProdutoCategoriaDTO categoria;

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getAnoDeLancamento() {
		return anoDeLancamento;
	}

	public void setAnoDeLancamento(String anoDeLancamento) {
		this.anoDeLancamento = anoDeLancamento;
	}

	public ProdutoCategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(ProdutoCategoriaDTO categoria) {
		this.categoria = categoria;
	}
	
	
}
