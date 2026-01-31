package com.distribuida.Service;

import com.distribuida.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<Pedido> findAll();

    Optional<Pedido> findOne(int id);

    Pedido save(Pedido pedido);

    Pedido update(int id, Pedido pedido);

    void delete(int id);
}
