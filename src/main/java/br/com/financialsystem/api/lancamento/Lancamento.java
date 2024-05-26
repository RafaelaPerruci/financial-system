package br.com.financialsystem.api.lancamento;

import br.com.financialsystem.api.categoria.Categoria;
import br.com.financialsystem.api.pessoa.Pessoa;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Table(name = "lancamento")
@Entity(name = "Lancamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    private BigDecimal valor;
    private String observacao;

    @Enumerated(EnumType.STRING)
    private TipoLancamento tipo;

    @ManyToOne
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_pessoa", nullable = false)
    private Pessoa pessoa;

    private boolean ativo;

    public Lancamento(DadosCadastroLancamento dados) {
        this.ativo = true;
        this.descricao = dados.descricao();
        this.tipo = dados.tipo();
        this.dataPagamento = dados.dataPagamento();
        this.dataVencimento = dados.dataVencimento();
        this.observacao = dados.observacao();
        this.valor = dados.valor();
    }

    public void atualizarInformacoes(DadosAtualizacaoLancamento dados) {
        if(dados.descricao() != null) {
            this.descricao = dados.descricao();
        }
        if(dados.dataPagamento() != null) {
            this.dataPagamento = dados.dataPagamento();
        }
        if(dados.dataVencimento() != null) {
            this.dataVencimento = dados.dataVencimento();
        }
        if(dados.valor() != null) {
            this.valor = dados.valor();
        }
        if(dados.observacao() != null) {
            this.observacao = dados.observacao();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}