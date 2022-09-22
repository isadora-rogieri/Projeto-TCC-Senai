package com.tccsenai.loucosporlanchesapp.model;

import java.math.BigDecimal;

public class Produto {


    public int  id;
    public  String nome;
    public  String descricao;
    public double preco;
    public  String byteImagem;

    public Produto() {
    }

    public Produto(int id, String nome, String descricao, double preco, String byteImagem) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.byteImagem = byteImagem;
    }

    public Produto(String nome, double preco, String descricao, String byteImagem) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.byteImagem = byteImagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getByteImagem() {
        return byteImagem;
    }

    public void setByteImagem(String byteImagem) {
        this.byteImagem = byteImagem;
    }
}
