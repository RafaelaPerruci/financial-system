package br.com.financialsystem.api.controller;

import br.com.financialsystem.api.categoria.Categoria;
import br.com.financialsystem.api.categoria.CategoriaRepository;
import br.com.financialsystem.api.lancamento.*;
import br.com.financialsystem.api.pessoa.PessoaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("lancamentos")
public class LancamentoController {

    @Autowired
    LancamentoRepository lancamentoRepository;
    @Autowired
    CategoriaRepository categoriaRepository;
    @Autowired
    PessoaRepository pessoaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroLancamento dados, UriComponentsBuilder uriBuilder) {
        var categoria = categoriaRepository.findByIdAndAtivoTrue(dados.idCategoria());
        var pessoa = pessoaRepository.findByIdAndAtivoTrue(dados.idPessoa());
        var lancamento = new Lancamento(dados);
        lancamento.setCategoria(categoria);
        lancamento.setPessoa(pessoa);
        lancamentoRepository.save(lancamento);
        var uri = uriBuilder.path("/lancamentos/{id}").buildAndExpand(lancamento.getId()).toUri();
        return ResponseEntity.created(uri).body(lancamento);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemLancamento>> listar(@PageableDefault(size = 10, sort
            = {"descricao"}) Pageable pageable) {
        var page = lancamentoRepository.findByAtivoTrue(pageable).map(DadosListagemLancamento::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarLancamento(@RequestBody @Valid DadosAtualizacaoLancamento dados) {
        var lancamento = lancamentoRepository.getReferenceById(dados.id());
        lancamento.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoLancamento(lancamento));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirLancamento(@PathVariable Long id) {
        var lancamento = lancamentoRepository.getReferenceById(id);
        lancamento.excluir();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var lancamento = lancamentoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoLancamento(lancamento));
    }
}
