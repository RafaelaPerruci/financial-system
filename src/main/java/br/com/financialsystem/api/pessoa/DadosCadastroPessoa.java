package br.com.financialsystem.api.pessoa;

import br.com.financialsystem.api.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroPessoa(@NotBlank
                                  String nome,
                                  @NotNull @Valid
                                  DadosEndereco endereco) {
}