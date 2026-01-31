package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PagosTestUnitaria {

    private Pagos pagos;
    @BeforeEach
    public void setUp(){
        pagos = new Pagos(1,"Tarjeta","20/09/2025");
    }

    @Test
    public void testPagosConstructor() {
        assertAll("Validar datos Pagos - Contructor",
                () -> assertEquals(1, pagos.getIdPago()),
                () -> assertEquals("Tarjeta", pagos.getMetodoPago()),
                () -> assertEquals("20/09/2025",pagos.getFechaPago())
        );
    }

    @Test
    public void testPagosSetters() {
        pagos.setIdPago(2);
        pagos.setMetodoPago("Efectivo");
        pagos.setFechaPago("01/12/2025");

        assertAll("Validar datos Pagos - Setters",
                () -> assertEquals(2,pagos.getIdPago()),
                () -> assertEquals("Efectivo",pagos.getMetodoPago()),
                () -> assertEquals("01/12/2025",pagos.getFechaPago())
        );
    }

    @Test
    public void testPagosToString() {
        String str = pagos.toString();

        assertAll("validar datos Pagos - ToString",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Tarjeta")),
                () -> assertTrue(str.contains("20/09/2025"))

        );

    }
}











