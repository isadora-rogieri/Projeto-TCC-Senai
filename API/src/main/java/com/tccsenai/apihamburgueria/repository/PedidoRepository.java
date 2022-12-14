package com.tccsenai.apihamburgueria.repository;

import com.tccsenai.apihamburgueria.model.ItemPedido;
import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Pedido findOneById(Integer id);



}
