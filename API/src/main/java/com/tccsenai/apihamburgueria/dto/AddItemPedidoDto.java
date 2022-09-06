package com.tccsenai.apihamburgueria.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddItemPedidoDto {

    protected Integer id;
    protected Integer produtoId;
    protected Integer quantidade;


}
