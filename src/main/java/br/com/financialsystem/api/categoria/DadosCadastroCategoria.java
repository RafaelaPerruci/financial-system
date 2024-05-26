package br.com.financialsystem.api.categoria;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroCategoria(@NotBlank String nome) {
}
