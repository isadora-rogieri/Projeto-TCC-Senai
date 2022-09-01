package com.tccsenai.apihamburgueria.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id",referencedColumnName = "id")
    protected Pedido pedido;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id",referencedColumnName = "id")
    protected Produto produto;
    @Column(nullable = false)
    protected BigDecimal precoProduto;
    @Column(nullable = false)
    protected Integer quantidade;



}
