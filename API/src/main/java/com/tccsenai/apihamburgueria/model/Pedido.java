package com.tccsenai.apihamburgueria.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Usuario_id",referencedColumnName = "id")
    protected Usuario cliente;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ItemPedido_id",referencedColumnName = "id")
    protected List<ItemPedido> itens;
    @Column(nullable = false)
    protected OffsetDateTime data;
    @Column(nullable = false)
    protected BigDecimal valorTotal;
}
