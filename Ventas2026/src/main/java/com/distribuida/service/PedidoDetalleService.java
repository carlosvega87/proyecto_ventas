package com.distribuida.service;

import com.distribuida.model.PedidoDetalle;

import java.util.List;
import java.util.Optional;

public interface PedidoDetalleService {

    List<PedidoDetalle> findAll();

    Optional<PedidoDetalle> findOne(int id);

    PedidoDetalle save(PedidoDetalle detalle);

    PedidoDetalle update(int id, PedidoDetalle detalle);

    void delete(int id);
}
