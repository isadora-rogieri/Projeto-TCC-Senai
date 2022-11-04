package com.tccsenai.apihamburgueria.controller;

import com.tccsenai.apihamburgueria.model.Produto;
import com.tccsenai.apihamburgueria.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/hamburgueria/produtos")
public class ProdutoController {

    @Autowired
    ProdutosService produtosService;


    @PostMapping
    public Produto cadastrarProduto(@Valid @RequestBody Produto produto){

        Produto response = produtosService.salvarProduto(produto);
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
    public Produto alterarProduto(@PathVariable("id") Integer id, @RequestBody Produto produto){
        
        //ResponseEntity response = new ResponseEntity("Produto alterado", HttpStatus.OK);
        return this.produtosService.atualizarProduto(id,produto);
    }

    @DeleteMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deletarProduto(@PathVariable("id") Integer id){

            this.produtosService.deletarProduto(id);
            Map<String, String> stringStringMap = new HashMap<>();
            stringStringMap.put("status", "successo");
            stringStringMap.put("message", "Deletado com sucesso");
            stringStringMap.put("code", "200");
            return ResponseEntity.status(HttpStatus.OK).
                    body(stringStringMap);

        }






}
