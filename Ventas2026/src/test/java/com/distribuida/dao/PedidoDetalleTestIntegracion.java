package com.distribuida.dao;

import com.distribuida.model.Pedido;
import com.distribuida.model.PedidoDetalle;
import com.distribuida.model.Productos;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class PedidoDetalleTestIntegracion {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductosRepository productoRepository;

    @Test
    public void testPedidoDetalleFindAll() {
        List<PedidoDetalle> detalles = pedidoDetalleRepository.findAll();
        assertNotNull(detalles);
        assertTrue(detalles.size() > 0);
        detalles.forEach(System.out::println);
    }

    @Test
    public void testPedidoDetalleFindOne() {
        Optional<PedidoDetalle> detalle = pedidoDetalleRepository.findById(1);
        assertTrue(detalle.isPresent());
    }

    @Test
    public void testPedidoDetalleSave() {

        Pedido pedido = pedidoRepository.findById(1).orElse(null);
        Productos producto = productoRepository.findById(1).orElse(null);

        PedidoDetalle pd = new PedidoDetalle(
                0,
                pedido,
                producto,
                2,
                producto.getPrecio() * 2
        );

        PedidoDetalle guardado = pedidoDetalleRepository.save(pd);

        assertNotNull(guardado);
    }

    @Test
    public void testPedidoDetalleActualizar() {
        Optional<PedidoDetalle> det = pedidoDetalleRepository.findById(52);
        assertTrue(det.isPresent());

        PedidoDetalle d = det.get();
        d.setCantidad(5);
        d.setSubtotal(d.getProducto().getPrecio() * 5);

        pedidoDetalleRepository.save(d);
    }

    @Test
    public void testPedidoDetalleBorrar() {
        pedidoDetalleRepository.deleteById(52);
    }
}
