package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTestUnitario {

    private Pedido pedido;
    private Cliente cliente;
    private Pagos pago;

    @BeforeEach
    public void setUp() {

        cliente = new Cliente(1, "Joseth", "Diaz", "diaz@gmail.com",
                "0999252904", "Tumbaco", new Date());

        pago = new Pagos(1, "Efectivo", "2024-12-01");

        pedido = new Pedido();
        pedido.setIdPedido(10);
        pedido.setCliente(cliente);
        pedido.setPago(pago);
        pedido.setFechaPedido(new Date());
        pedido.setEstado("PENDIENTE");
        pedido.setTotal(120.0);
        pedido.setIva(20.0);
        pedido.setTotalNeto(100.0);
    }

    @Test
    public void testPedidoSetters() {
        assertAll("Validar Pedido con setters",
                () -> assertEquals(10, pedido.getIdPedido()),
                () -> assertEquals("PENDIENTE", pedido.getEstado()),
                () -> assertEquals(120.0, pedido.getTotal()),
                () -> assertEquals(20.0, pedido.getIva()),
                () -> assertEquals(100.0, pedido.getTotalNeto()),
                () -> assertEquals(cliente, pedido.getCliente()),
                () -> assertEquals(pago, pedido.getPago())
        );
    }

    @Test
    public void testPedidoToString() {
        String str = pedido.toString();

        assertAll("Validar toString Pedido",
                () -> assertTrue(str.contains("10")),
                () -> assertTrue(str.contains("PENDIENTE")),
                () -> assertTrue(str.contains("120.0")),
                () -> assertTrue(str.contains("20.0")),
                () -> assertTrue(str.contains("100.0"))
        );
    }
}
