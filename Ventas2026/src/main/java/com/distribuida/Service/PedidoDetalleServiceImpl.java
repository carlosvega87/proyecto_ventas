package com.distribuida.Service;

import com.distribuida.dao.PedidoDetalleRepository;
import com.distribuida.model.PedidoDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    @Override
    public List<PedidoDetalle> findAll() {
        return pedidoDetalleRepository.findAll();
    }

    @Override
    public Optional<PedidoDetalle> findOne(int id) {
        return pedidoDetalleRepository.findById(id);
    }

    @Override
    public PedidoDetalle save(PedidoDetalle detalle) {
        return pedidoDetalleRepository.save(detalle);
    }

    @Override
    public PedidoDetalle update(int id, PedidoDetalle detalle) {

        Optional<PedidoDetalle> detExistente = pedidoDetalleRepository.findById(id);

        if (detExistente.isEmpty()) {
            return null;
        }

        PedidoDetalle d = detExistente.get();

        // Actualizamos solo campos reales
        d.setCantidad(detalle.getCantidad());
        d.setSubtotal(detalle.getSubtotal());
        d.setProducto(detalle.getProducto());
        d.setPedido(detalle.getPedido());

        return pedidoDetalleRepository.save(d);
    }

    @Override
    public void delete(int id) {
        if (pedidoDetalleRepository.existsById(id)) {
            pedidoDetalleRepository.deleteById(id);
        }
    }
}
