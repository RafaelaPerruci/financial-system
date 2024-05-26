package br.com.financialsystem.api.categoria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Page<Categoria> findByAtivoTrue (Pageable pageable);
//    Categoria findByIdAndAtivoTrue (Long id);
}
