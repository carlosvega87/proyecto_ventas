package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ProductosTestUnitaria {

    private Productos productos;
    private Categoria categoria;

    @BeforeEach
    public void setUp() {

        categoria = new Categoria(2, "Juegos y Entretenimiento");
        productos = new Productos(1, "Play Station 5", 250.00, 20, "19/1/2025", "Disponible", categoria);
    }

    @Test
    public void TestProductosConstructor() {
        assertAll("Validar datos Productos-Constructor",
                () -> assertEquals(1, productos.getIdProducto()),
                () -> assertEquals("Play Station 5", productos.getNombre()),
                () -> assertEquals(250.0, productos.getPrecio()),
                () -> assertEquals(20, productos.getStock()),
                () -> assertEquals("19/1/2025", productos.getFechaRegistro()),
                () -> assertEquals("Disponible", productos.getEstado())
        );
    }

    @Test
    public void TestProductosSetters() {
        categoria = new Categoria(4, "Juegos y Entretenimiento");
        productos = new Productos(2, "Teclado Gamer RGB", 25.00, 10, "19/1/2025", "Disponible", categoria);

        productos.setIdProducto(2);
        productos.setNombre("Teclado Gamer RGB");
        productos.setPrecio(25.00);
        productos.setStock(10);
        productos.setFechaRegistro("19/1/2025");
        productos.setEstado("Disponible");
        productos.setCategoria(categoria);

        assertAll("Validar datos Productos Setters",
                () -> assertEquals(2, productos.getIdProducto()),
                () -> assertEquals("Teclado Gamer RGB", productos.getNombre()),
                () -> assertEquals(25.00, productos.getPrecio()),
                () -> assertEquals(10, productos.getStock()),
                () -> assertEquals("19/1/2025", productos.getFechaRegistro()),
                () -> assertEquals("Disponible", productos.getEstado()),
                () -> assertEquals(categoria, productos.getCategoria())
        );
    }
    @Test
    public void TestProductosToString(){
        String str= productos.toString();
        assertAll("Validar to String - Productos",
                ()->assertTrue(str.contains("1")),
                ()->assertTrue(str.contains("Play Station 5")),
                ()->assertTrue(str.contains("250.0")),
                ()->assertTrue(str.contains("20")),
                ()->assertTrue(str.contains("19/1/2025")),
                ()->assertTrue(str.contains("Disponible"))


        );

    }
}