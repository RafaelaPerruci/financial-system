package br.com.financialsystem.api.lancamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosAtualizacaoLancamento(
        @NotNull Long id,
        String descricao,
        @NotNull LocalDate dataVencimento,
        @NotNull LocalDate dataPagamento,
        @Positive BigDecimal valor,
        @Size(max = 200, message = "A observação não pode exceder 200 caracteres.") String observacao) {
}
