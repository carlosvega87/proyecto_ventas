package com.distribuida.dao;

import com.distribuida.model.Pedido;
import com.distribuida.model.Cliente;
import com.distribuida.model.Pagos;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(false)
public class PedidoTestIntegracion {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PagosRepository pagosRepository;

    @Test
    public void testPedidoFindAll() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        assertNotNull(pedidos);
        assertTrue(pedidos.size() > 0);
        pedidos.forEach(System.out::println);
    }

    @Test
    public void testPedidoFindOne() {
        Optional<Pedido> pedido = pedidoRepository.findById(1);
        assertTrue(pedido.isPresent());
        System.out.println(pedido.get());
    }

    @Test
    public void testPedidoSave() {

        Cliente cliente = clienteRepository.findById(1).orElse(null);
        Pagos pago = pagosRepository.findById(1).orElse(null);

        Pedido pedido = new Pedido(
                0,
                cliente,
                pago,
                new Date(),
                "CREADO",
                150.50,
                18.06,
                168.56
        );

        Pedido guardado = pedidoRepository.save(pedido);

        assertNotNull(guardado);
        assertEquals(150.50, guardado.getTotal());
    }

    @Test
    public void testPedidoActualizar() {
        Optional<Pedido> pedido = pedidoRepository.findById(52);
        assertTrue(pedido.isPresent());

        Pedido p = pedido.get();
        p.setEstado("ACTUALIZADO");
        p.setTotal(200.0);

        pedidoRepository.save(p);
    }

    @Test
    public void testPedidoBorrar() {
        pedidoRepository.deleteById(52);
    }
}
