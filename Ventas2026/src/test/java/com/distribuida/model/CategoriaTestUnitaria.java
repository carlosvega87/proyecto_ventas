package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoriaTestUnitaria {

    private Categoria categoria;
    @BeforeEach
    public void setUp(){
        categoria = new Categoria(1, "Gamer");
    }

    @Test

    public void testCategoriaConstructor(){

        assertAll("Validar datos cliente - constructor",
                () -> assertEquals(1, categoria.getIdCategoria()) ,
                () -> assertEquals("Gamer", categoria.getNombreCategoria())


        );
    }
    @Test
    public void testClienteSetters(){
        categoria.setIdCategoria(2);
        categoria.setNombreCategoria("Casual");




        assertAll("Validar datos categoria - seter",
                () -> assertEquals(2, categoria.getIdCategoria()) ,
                () -> assertEquals("Casual", categoria.getNombreCategoria())



        );

    }
    @Test
    public void categoriaTestToString(){
        String str = categoria.toString();
        assertAll("Validar datos categoria - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Gamer"))

        );
    }
}
