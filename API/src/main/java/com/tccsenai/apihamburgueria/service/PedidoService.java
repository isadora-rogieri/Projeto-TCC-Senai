package com.tccsenai.apihamburgueria.service;

import com.tccsenai.apihamburgueria.dto.AddItemPedidoDto;
import com.tccsenai.apihamburgueria.dto.ItemPedidoDto;
import com.tccsenai.apihamburgueria.dto.PedidoDto;
import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.model.Produto;
import com.tccsenai.apihamburgueria.model.Usuario;
import com.tccsenai.apihamburgueria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    ProdutosService produtosService;

    @Autowired
    PedidoRepository pedidoRepository;

    public  void addPedido(AddItemPedidoDto addItemPedidoDto, Usuario cliente){
        Produto produto = produtosService.buscaProdutoById(addItemPedidoDto.getId());

        Pedido pedido = new Pedido();
        pedido.setProduto(produto);
        pedido.setUsuario(cliente);
        pedido.setQuantidade(addItemPedidoDto.getQuantidade());
        pedido.setData(LocalDate.now());

        pedidoRepository.save(pedido);
    }
    public PedidoDto listPedidosItems(Usuario cliente) {
        List<Pedido> pedidoList = pedidoRepository.findAllByUsuario(cliente);

        List<ItemPedidoDto> pedidoItens = new ArrayList<>();
        BigDecimal valorTotal = BigDecimal.ZERO;
        for (Pedido itemPedido: pedidoList) {
            ItemPedidoDto itemPedidoDto = new ItemPedidoDto(itemPedido);
            valorTotal = BigDecimal.valueOf(itemPedidoDto.getQuantidade()).multiply(itemPedido.getProduto().getPreco());

            pedidoItens.add(itemPedidoDto);
        }

        PedidoDto pedidoDto = new PedidoDto();
        pedidoDto.setValorTotal(valorTotal);
        pedidoDto.setItens(pedidoItens);
        return  pedidoDto;
    }

    public List<Pedido> listTodosPedidos() {
        List<Pedido> pedidoList = pedidoRepository.findAll();

        return  pedidoList;
    }

}
