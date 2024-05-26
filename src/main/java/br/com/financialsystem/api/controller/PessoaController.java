package br.com.financialsystem.api.controller;

import br.com.financialsystem.api.lancamento.DadosCadastroLancamento;
import br.com.financialsystem.api.pessoa.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    PessoaRepository pessoaRepository;

    @PostMapping
    @Transactional
    public void cadastrarPessoa(@RequestBody @Valid DadosCadastroPessoa pessoa) {
        pessoaRepository.save(new Pessoa(pessoa));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemPessoa>> listarPessoas(@PageableDefault(size = 10, sort
            = {"nome"}) Pageable pageable) {
        var page = pessoaRepository.findByAtivoTrue(pageable).map(DadosListagemPessoa::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarPessoa(@RequestBody @Valid DadosAtualizacaoPessoa dados) {
        var pessoa = pessoaRepository.getReferenceById(dados.id());
        pessoa.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPessoa(pessoa));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirPessoa(@PathVariable Long id) {
       var pessoa = pessoaRepository.getReferenceById(id);
       pessoa.excluir();
       return ResponseEntity.noContent().build();
    }


}
