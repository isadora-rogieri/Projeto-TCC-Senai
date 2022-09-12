package com.tccsenai.apihamburgueria.controller;

import com.tccsenai.apihamburgueria.dto.PedidoDto;
import com.tccsenai.apihamburgueria.model.ItemPedido;
import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.model.Produto;
import com.tccsenai.apihamburgueria.service.ItemPedidoService;
import com.tccsenai.apihamburgueria.service.PedidoService;
import com.tccsenai.apihamburgueria.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    ItemPedidoService itemPedidoService;


    @GetMapping
    public List<Pedido> getPedidos(){
        return pedidoService.listarTodos();
    }
    @GetMapping("/{id}")
    public  Pedido getDetalhesPedidos(@PathVariable("id") Integer id){
        return  pedidoService.findById(id);
    }

    @GetMapping("/itens/{id}")
    public  List<ItemPedido> getItensPedidos(@PathVariable("id") Integer id){
        return  itemPedidoService.listPedido(id);
    }

    @PostMapping("/add")
    public ResponseEntity addItens(@RequestBody Pedido pedido){

        pedidoService.insert(pedido);

        return  new ResponseEntity("Produto adicionado", HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody PedidoDto dto) {
        Pedido pedido = pedidoService.fromDto(dto);
        pedidoService.save(pedido);

        return  new ResponseEntity("Produto adicionado", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable Integer id, @Valid @RequestBody PedidoDto pedidoDto) {
        pedidoService.save(pedidoService.fromDto(pedidoDto));
        return new ResponseEntity("Pedido alterado", HttpStatus.OK);
    }
}
