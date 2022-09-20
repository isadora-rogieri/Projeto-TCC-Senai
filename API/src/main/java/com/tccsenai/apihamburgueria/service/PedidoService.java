package com.tccsenai.apihamburgueria.service;

import com.tccsenai.apihamburgueria.dto.ItemPedidoDto;
import com.tccsenai.apihamburgueria.dto.PedidoDto;
import com.tccsenai.apihamburgueria.enums.StatusPedido;
import com.tccsenai.apihamburgueria.model.ItemPedido;
import com.tccsenai.apihamburgueria.model.Pedido;
import com.tccsenai.apihamburgueria.model.Usuario;
import com.tccsenai.apihamburgueria.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoService itemPedidoService;

    @Autowired
    private UsuarioService usuarioService;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido findById(Integer id) {
        return  pedidoRepository.findOneById(id);
    }

    @Transactional
    public void inserir(Pedido pedido) {
        pedido.setData(LocalDate.now());
        pedido.setStatusPedido(StatusPedido.EM_PREPARO);
        pedidoRepository.save(pedido);
    }

    @Transactional
    public void delete(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido fromDto(PedidoDto pedidoDto) {
        List<ItemPedido> itemPedidoList = new ArrayList<>();

        Pedido pedido = new Pedido();
        pedido.setId(pedidoDto.getId());
        pedido.setUsuario(usuarioService.buscaUsuarioById(pedidoDto.getUsuarioId()));

        ItemPedido itemPedido ;
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoDto itemPedidoDto : pedidoDto.getItensPedido()) {
            itemPedido = itemPedidoService.fromDto(itemPedidoDto);
            itemPedido.setPedido(pedido);

            var produto = itemPedidoService.fromDto(itemPedidoDto).getProduto();

            itemPedidoList.add(itemPedido);
            valorTotal = BigDecimal.valueOf(itemPedidoDto.getQuantidade()).multiply(produto.getPreco()).add(valorTotal);
        }
        pedido.setItensPedido(itemPedidoList);
        pedido.setValorTotal(valorTotal);
        pedido.setFormaPagamento(pedidoDto.getFormaPagamento());

        return pedido;
    }

    public void alterarStatus(Integer id, StatusPedido statusPedido) {
        Pedido pedido = pedidoRepository.getById(id);
        pedido.setStatusPedido(statusPedido);

        pedidoRepository.save(pedido);
    }

}
