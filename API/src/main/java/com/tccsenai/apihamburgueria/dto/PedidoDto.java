package com.tccsenai.apihamburgueria.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDto {

    protected List<ItemPedidoDto> itens;
    protected BigDecimal valorTotal;

}
