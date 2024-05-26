package br.com.financialsystem.api.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    Page<Lancamento> findByAtivoTrue (Pageable paginacao);
}
