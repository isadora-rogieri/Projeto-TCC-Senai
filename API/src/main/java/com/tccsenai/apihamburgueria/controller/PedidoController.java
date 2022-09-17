package com.tccsenai.apihamburgueria.controller;

import com.tccsenai.apihamburgueria.dto.PedidoDto;
import com.tccsenai.apihamburgueria.enums.StatusPedido;
import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.service.ItemPedidoService;
import com.tccsenai.apihamburgueria.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/hamburgueria/pedidos")
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

    @PostMapping
    public ResponseEntity insert(@Valid @RequestBody PedidoDto dto) {
        Pedido pedido = pedidoService.fromDto(dto);
        pedidoService.save(pedido);

        return  new ResponseEntity("Produto adicionado", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePedido(@PathVariable("id") Integer id) {

        pedidoService.delete(id);

        return  new ResponseEntity("Pedido Excluido", HttpStatus.OK);
    }

    @PutMapping(path = "/{id}/status")
    public ResponseEntity alterarStatus(@PathVariable("id") Integer id, @Valid @RequestParam StatusPedido status) {
        pedidoService.updateStatus(id, status);
        return  new ResponseEntity("Status Pedido Alterado", HttpStatus.OK);
    }



}
