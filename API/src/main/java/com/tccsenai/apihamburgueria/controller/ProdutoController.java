package com.tccsenai.apihamburgueria.controller;

import com.tccsenai.apihamburgueria.model.Produto;
import com.tccsenai.apihamburgueria.model.Usuario;
import com.tccsenai.apihamburgueria.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hamburgueria/produtos")
public class ProdutoController {

    @Autowired
    ProdutosService produtosService;


    @PostMapping
    public ResponseEntity cadastrarProduto(@Valid @RequestBody Produto produto){
           produtosService.salvarProduto(produto);

            ResponseEntity response = new ResponseEntity("Produto criado", HttpStatus.CREATED);

        return response;
    }

    @GetMapping
    public List<Produto> getProdutos(){
        return produtosService.litarTodos();
    }

    @GetMapping("/{id}")
    public  Produto getProduto(@PathVariable("id") Integer id){
        return  produtosService.buscaProdutoById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity alterarProduto(@PathVariable("id") Integer id, @RequestBody Produto produto){
        this.produtosService.atualizarProduto(id,produto);
        ResponseEntity response = new ResponseEntity("Produto alterado", HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarProduto(@PathVariable("id") Integer id){
        this.produtosService.deletarProduto(id);
        ResponseEntity response = new ResponseEntity("Produto Deletado", HttpStatus.OK);
        return response;
    }


}
