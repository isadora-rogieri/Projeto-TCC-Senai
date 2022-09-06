package com.tccsenai.apihamburgueria.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
    protected Usuario usuario;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produto_id",referencedColumnName = "id")
    protected Produto produto;
    @Column(nullable = false)
    protected LocalDate data;
    @Column(nullable = false)
    protected int quantidade;
}
