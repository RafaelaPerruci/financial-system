package br.com.financialsystem.api.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(@NotBlank String logradouro,
                            String numero,
                            String complemento,
                            @NotBlank String bairro,
                            @NotBlank @Pattern(regexp = "\\d{8}") String cep,
                            @NotBlank String cidade,
                            @NotBlank String uf) {
}