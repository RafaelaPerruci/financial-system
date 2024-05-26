package br.com.financialsystem.api.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCategoria(@NotNull Long id,
                                        @NotBlank String nome) {
}
