package br.com.financialsystem.api.pessoa;

import br.com.financialsystem.api.endereco.Endereco;

public record DadosDetalhamentoPessoa(Long id,
                                      String nome,
                                      Endereco endereco) {

    public DadosDetalhamentoPessoa(Pessoa pessoa) {
        this(pessoa. getId(),
                pessoa.getNome(),
                pessoa.getEndereco());
    }
}
