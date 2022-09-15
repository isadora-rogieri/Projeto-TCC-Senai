package com.tccsenai.apihamburgueria.service;

import com.tccsenai.apihamburgueria.dto.ItemPedidoDto;
import com.tccsenai.apihamburgueria.model.ItemPedido;
import com.tccsenai.apihamburgueria.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository repository;

    @Autowired
    private ProdutosService produtoService;

    public List<ItemPedido> list() {
        return repository.findAll();
    }

    public List<ItemPedido> listPedido(Integer id) {
        return repository.findAllByPedidoId(id);
    }

    public ItemPedido findById(Integer id) {
        Optional<ItemPedido> itemPedido = repository.findById(id);
        return itemPedido.orElseThrow();
    }

    public void delete(ItemPedido itemPedido) {
        itemPedido.getPedido().getItens().remove(itemPedido);
        repository.deleteById(itemPedido.getId());
    }

    public ItemPedido fromDto(ItemPedidoDto itemPedidoDto) {
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setId(itemPedidoDto.getId());
        itemPedido.setQuantidade(itemPedidoDto.getQuantidade());
        var produto = produtoService.buscaProdutoById(itemPedidoDto.getProdutoId());

        itemPedido.setProduto(produto);
        itemPedido.setValor(produto.getPreco().multiply(BigDecimal.valueOf(itemPedido.getQuantidade())));

        return itemPedido;
    }


}