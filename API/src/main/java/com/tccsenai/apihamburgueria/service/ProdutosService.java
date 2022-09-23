package com.tccsenai.apihamburgueria.service;

import com.tccsenai.apihamburgueria.model.Produto;
import com.tccsenai.apihamburgueria.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutosService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto salvarProduto(Produto produto){
        produtoRepository.save(produto);
        return produto;
    }
    public List<Produto> litarTodos() {
        return produtoRepository.findAll();
    }
    public void atualizarProduto(Integer id, Produto produto){
        Produto produto1 = this.produtoRepository.findById(id).orElseThrow();
        produto1.setNome(produto.getNome());
        produto1.setDescricao(produto.getDescricao());
        produto1.setPreco(produto.getPreco());

        this.produtoRepository.save(produto1);
    }

    public void deletarProduto(Integer id){
        Produto produto = this.produtoRepository.findById(id).orElseThrow();
        this.produtoRepository.delete(produto);
    }

    public Produto buscaProdutoById(Integer id) {
        return this.produtoRepository.findById(id).orElseThrow();
    }

}
