package br.com.financialsystem.api.pessoa;

import br.com.financialsystem.api.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPessoa(
        @NotNull Long id,
        String nome,
        DadosEndereco endereco) {
}
