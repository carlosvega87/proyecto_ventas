package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MovimientoInventarioTestUnitaria {
    private MovimientoInventario movimientoInventario;
    private Productos productos;
    private Categoria categoria;
    @BeforeEach
    public  void setUp(){
        categoria = new Categoria(2, "Juegos y Entretenimiento");
        productos = new Productos(1, "Play Station 5", 250.00, 20, "19/1/2025", "Disponible", categoria);
        movimientoInventario =new MovimientoInventario(1,"Entrada",10,"19/1/2025",productos);
    }

    @Test
    public void TestMovimientoInventarioConstructor(){
        assertAll("Validar datos MovimientoInventario-Constructor",
        ()-> assertEquals(1,movimientoInventario.getIdMovimiento()),
        ()-> assertEquals("Entrada",movimientoInventario.getTipo()),
        ()-> assertEquals(10,movimientoInventario.getCantidad()),
        ()-> assertEquals("19/1/2025",movimientoInventario.getFechaMovimiento())
         );
    }
    @Test
    public void TestMovimientoInventarioSetters(){

        categoria = new Categoria(2, "Juegos y Entretenimiento");
        productos = new Productos(1, "Play Station 5", 250.00, 20, "19/1/2025", "Disponible", categoria);
        movimientoInventario =new MovimientoInventario(2,"Salida",5,"10/1/2025",productos);

        movimientoInventario.setIdMovimiento(2);
        movimientoInventario.setTipo("Salida");
        movimientoInventario.setCantidad(5);
        movimientoInventario.setFechaMovimiento("10/1/2025");

        assertAll("Validar datos MovimientoInventario- Setters",
                ()-> assertEquals(2,movimientoInventario.getIdMovimiento()),
                ()-> assertEquals("Salida",movimientoInventario.getTipo()),
                ()-> assertEquals(5,movimientoInventario.getCantidad()),
                ()-> assertEquals("10/1/2025",movimientoInventario.getFechaMovimiento())
                );
    }
    @Test
    public void TestMovimientoInventarioToString() {
        String str = movimientoInventario.toString();
        assertAll("Validar to String - MovimientoInventario",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Entrada")),
                () -> assertTrue(str.contains("10")),
                () -> assertTrue(str.contains("19/1/2025"))
        );
    }
}
