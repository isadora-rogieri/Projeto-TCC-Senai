package com.tccsenai.apihamburgueria.repository;

import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findAllByUsuario(Usuario usuario);
}
