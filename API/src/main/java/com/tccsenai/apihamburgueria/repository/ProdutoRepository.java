package com.tccsenai.apihamburgueria.repository;

import com.tccsenai.apihamburgueria.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findById(Integer id);
}
