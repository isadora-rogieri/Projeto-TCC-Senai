package com.tccsenai.apihamburgueria.controller;

import com.tccsenai.apihamburgueria.dto.AddItemPedidoDto;
import com.tccsenai.apihamburgueria.dto.PedidoDto;
import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.model.Usuario;
import com.tccsenai.apihamburgueria.service.PedidoService;
import com.tccsenai.apihamburgueria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/add")
    public ResponseEntity addPedido(@RequestBody AddItemPedidoDto addItemPedidoDto, Integer clienteId ) {
        Usuario cliente = usuarioService.buscaUsuarioById(clienteId);

        pedidoService.addPedido(addItemPedidoDto, cliente);

        return new ResponseEntity( "Adicionado ao Pedido", HttpStatus.CREATED);
    }

    @GetMapping("/cliente")
    public ResponseEntity<PedidoDto> getItems(@RequestParam(name = "clienteId") Integer clienteId) {
        Usuario cliente = usuarioService.buscaUsuarioById(clienteId);

        PedidoDto pedidoDto = pedidoService.listPedidosItems(cliente);
        return new ResponseEntity<>(pedidoDto, HttpStatus.OK);
    }

    @GetMapping
    public List<Pedido> getTodosPedidos() {

        return  pedidoService.listTodosPedidos();
    }



}
