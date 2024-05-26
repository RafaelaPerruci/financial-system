package br.com.financialsystem.api.lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public record DadosDetalhamentoLancamento(Long id,
                                          String descricao,
                                          LocalDate dataVencimento,
                                          LocalDate dataPagamento,
                                          BigDecimal valor,
                                          String observacao,
                                          TipoLancamento tipo,
                                          Long idCategoria,
                                          Long idPessoa) {

    public DadosDetalhamentoLancamento(Lancamento lancamento) {
        this(lancamento. getId(),
                lancamento.getDescricao(),
                lancamento.getDataVencimento(),
                lancamento.getDataPagamento(),
                lancamento.getValor(),
                lancamento.getObservacao(),
                lancamento.getTipo(),
                lancamento.getPessoa().getId(),
                lancamento.getCategoria().getId());
    }
}
