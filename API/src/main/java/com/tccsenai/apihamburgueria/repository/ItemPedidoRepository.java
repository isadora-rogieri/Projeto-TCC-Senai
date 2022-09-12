package com.tccsenai.apihamburgueria.repository;

import com.tccsenai.apihamburgueria.model.ItemPedido;
import com.tccsenai.apihamburgueria.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

    List<ItemPedido> findByPedido(Pedido pedido);

    List<ItemPedido> findAllByPedidoId(Integer id);
}
