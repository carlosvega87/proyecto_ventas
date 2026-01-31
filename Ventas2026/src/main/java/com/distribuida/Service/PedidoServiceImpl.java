package com.distribuida.Service;

import com.distribuida.dao.PedidoRepository;
import com.distribuida.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> findOne(int id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Pedido update(int id, Pedido pedido) {

        Optional<Pedido> pedExistente = pedidoRepository.findById(id);

        if (pedExistente.isEmpty()) {
            return null;
        }

        Pedido p = pedExistente.get();

        // Actualizamos solo los campos que existen
        p.setFechaPedido(pedido.getFechaPedido());
        p.setCliente(pedido.getCliente());
        p.setPago(pedido.getPago());
        p.setEstado(pedido.getEstado());
        p.setTotal(pedido.getTotal());
        p.setIva(pedido.getIva());
        p.setTotalNeto(pedido.getTotalNeto());

        // Relación OneToMany
        p.setDetalles(pedido.getDetalles());

        return pedidoRepository.save(p);
    }

    @Override
    public void delete(int id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
        }
    }
}
