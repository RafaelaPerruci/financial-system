package br.com.financialsystem.api.controller;

import br.com.financialsystem.api.categoria.*;
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
@RequestMapping("categoria")
public class CategoriaController {

    @Autowired
    CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarCategoria(@RequestBody @Valid DadosCadastroCategoria dados, UriComponentsBuilder uriBuilder) {
        var categoria = new Categoria(dados);
        categoriaRepository.save(categoria);
        var uri = uriBuilder.path("categoria/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoCategoria(categoria));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCategoria>> listarCategorias(@PageableDefault(size = 10, sort
            = {"nome"}) Pageable pageable) {
        var page =  categoriaRepository.findByAtivoTrue(pageable).map(DadosListagemCategoria::new);
        return ResponseEntity.ok(page);

    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizarCategoria(@RequestBody @Valid DadosAtualizacaoCategoria dados) {
        var categoria = categoriaRepository.getReferenceById(dados.id());
        categoria.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluirCategoria(@PathVariable Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        categoria.excluir();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        var categoria = categoriaRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCategoria(categoria));
    }
}
