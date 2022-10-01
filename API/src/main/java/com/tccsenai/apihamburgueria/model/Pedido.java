package com.tccsenai.apihamburgueria.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.tccsenai.apihamburgueria.enums.FormaPagamento;
import com.tccsenai.apihamburgueria.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "Usuario_id",referencedColumnName = "id")
    protected Usuario usuario;

    @JsonIgnoreProperties("pedido")
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<ItemPedido> itens = new ArrayList<>();

    @Column(nullable = false)
    protected LocalDate data;

    protected BigDecimal valorTotal;

    protected StatusPedido statusPedido;

    protected FormaPagamento formaPagamento;

    public Pedido() {}

    public Pedido(Integer id, Usuario usuario, List<ItemPedido> itens, LocalDate data, BigDecimal valorTotal, StatusPedido statusPedido) {
        this.id = id;
        this.usuario = usuario;
        this.itens = itens;
        this.data = data;
        this.valorTotal = valorTotal;
        this.statusPedido = statusPedido;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itens = itensPedido;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }
}
