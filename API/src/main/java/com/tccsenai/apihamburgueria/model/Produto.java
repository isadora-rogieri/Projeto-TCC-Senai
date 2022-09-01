package com.tccsenai.apihamburgueria.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(nullable = false)
    @NotBlank(message = "Nome obrigatório")
    @Size(min = 5, max = 100)
    protected String nome;
    @Column(nullable = false)
    @NotBlank(message = "Descrição obrigatório")
    @Size(min = 5, max = 100)
    protected String descricao;

    @Column(nullable = false)
    @NotNull(message = "Preço obrigatório")
    protected double preco;

    protected String caminhoImagem;

    protected String byteImagem;

}
