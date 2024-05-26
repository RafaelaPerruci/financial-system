package br.com.financialsystem.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Embeddable
@Getter
@AllArgsConstructor

public class Endereco {
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;

    public Endereco() {

    }
    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.complemento = dados.complemento();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.uf = dados.uf();
    }

    public void atualizarInformacoes(DadosEndereco dados) {
        if(dados.cep() != null) {
            this.cep = dados.cep();
        }
        if(dados.uf() != null) {
            this.uf = dados.uf();
        }
        if(dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if(dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }
        if(dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        if(dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if(dados.numero() != null) {
            this.numero = dados.numero();
        }
    }

}
