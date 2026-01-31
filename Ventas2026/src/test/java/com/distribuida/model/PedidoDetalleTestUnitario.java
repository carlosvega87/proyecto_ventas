package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoDetalleTestUnitario {

    private PedidoDetalle detalle;
    private Pedido pedido;
    private Productos producto;

    @BeforeEach
    public void setUp() {

        producto = new Productos();
        producto.setIdProducto(1);
        producto.setNombre("Laptop");
        producto.setPrecio(500.0);
        producto.setStock(10);

        pedido = new Pedido();
        pedido.setIdPedido(5);
        pedido.setEstado("PENDIENTE");

        detalle = new PedidoDetalle();
        detalle.setIdDetalle(100);
        detalle.setCantidad(2);
        detalle.setProducto(producto);
        detalle.setPedido(pedido);
        detalle.setSubtotal(1000.0); // 500 * 2
    }

    @Test
    public void testPedidoDetalleConstructorYSetters() {
        assertAll("Validar constructor y setters PedidoDetalle",
                () -> assertEquals(100, detalle.getIdDetalle()),
                () -> assertEquals(2, detalle.getCantidad()),
                () -> assertEquals(1000.0, detalle.getSubtotal()),
                () -> assertEquals(producto, detalle.getProducto()),
                () -> assertEquals(pedido, detalle.getPedido())
        );
    }

    @Test
    public void testPedidoDetalleSubtotal() {
        double subtotalEsperado = producto.getPrecio() * detalle.getCantidad();
        assertEquals(subtotalEsperado, detalle.getSubtotal());
    }

    @Test
    public void testToString() {
        String str = detalle.toString();

        assertAll("Validar toString PedidoDetalle",
                () -> assertTrue(str.contains("100")),
                () -> assertTrue(str.contains("2")),
                () -> assertTrue(str.contains("1000.0"))
        );
    }
}
