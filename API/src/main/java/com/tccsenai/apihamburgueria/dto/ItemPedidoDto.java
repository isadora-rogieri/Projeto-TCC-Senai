package com.tccsenai.apihamburgueria.dto;

import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.model.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDto {

    protected Integer id;
    protected Integer quantity;
    protected Produto produto;

    public ItemPedidoDto(Pedido pedido){
        this.id = pedido.getId();
        this.quantity = pedido.getQuantidade();
        this.setProduto(pedido.getProduto());

    }
}
