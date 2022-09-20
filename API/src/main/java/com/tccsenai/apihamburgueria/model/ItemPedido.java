package com.tccsenai.apihamburgueria.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id",referencedColumnName = "id")
    protected Pedido pedido;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "produto_id",referencedColumnName = "id")
    protected Produto produto;

    @Column(nullable = false)
    protected BigDecimal valor;

    @Column(nullable = false)
    protected Integer quantidade;

    public ItemPedido(Integer id, Pedido pedido, Produto produto, BigDecimal valor, Integer quantidade) {
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    private void setValor() {
        if (this.quantidade != null && this.produto != null) {
            this.valor = produto.getPreco().multiply(BigDecimal.valueOf(this.quantidade));
        }
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
