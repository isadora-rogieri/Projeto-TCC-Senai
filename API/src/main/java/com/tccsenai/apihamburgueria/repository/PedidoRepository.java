package com.tccsenai.apihamburgueria.repository;

import com.tccsenai.apihamburgueria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
