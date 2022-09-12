package com.tccsenai.apihamburgueria.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PedidoDto {

    protected  Integer id;

    protected  Integer usuarioId;

    protected List<ItemPedidoDto> itensPedido = new ArrayList<>();
}
