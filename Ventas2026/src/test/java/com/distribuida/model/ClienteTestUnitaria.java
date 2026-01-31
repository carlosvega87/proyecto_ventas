package com.distribuida.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ClienteTestUnitaria {
private Cliente cliente;
@BeforeEach
    public void setUp(){
cliente = new Cliente(1, "Anthony","Abad", "anthony@gmail.com", "0999252904", "Tumbaco", new Date() );
}

    @Test

    public void testClienteConstructor(){

        assertAll("Validar datos cliente - constructor",
                () -> assertEquals(1, cliente.getIdCliente()) ,
                () -> assertEquals("Anthony", cliente.getNombre()) ,
                () -> assertEquals("Abad", cliente.getApellido()) ,
                () -> assertEquals("anthony@gmail.com", cliente.getEmail()) ,
                () -> assertEquals("0999252904", cliente.getTelefono() ),
                () -> assertEquals("Tumbaco", cliente.getDireccion())

        );
    }
    @Test
    public void testClienteSetters(){
        cliente.setIdCliente(2);
        cliente.setNombre("Pedro");
        cliente.setApellido("Benavides");
        cliente.setEmail("pedrob@gmail.com");
        cliente.setTelefono("091234568");
        cliente.setDireccion("Cumbaya");
        cliente.setFechaRegistro(new Date());



        assertAll("Validar datos cliente - constructor",
                () -> assertEquals(2, cliente.getIdCliente()) ,
                () -> assertEquals("Pedro", cliente.getNombre()) ,
                () -> assertEquals("Benavides", cliente.getApellido()) ,
                () -> assertEquals("pedrob@gmail.com", cliente.getEmail()),
                () -> assertEquals("091234568", cliente.getTelefono()),
                () -> assertEquals("Cumbaya", cliente.getDireccion() )


        );

    }
    @Test
    public void clienteTestToString(){
        String str = cliente.toString();
        assertAll("Validar datos cliente - To String",
                () -> assertTrue(str.contains("1")),
                () -> assertTrue(str.contains("Anthony")),
                () -> assertTrue(str.contains("Abad")),
                () -> assertTrue(str.contains("anthony@gmail.com")),
                () -> assertTrue(str.contains("0999252904")),
                () -> assertTrue(str.contains("Tumbaco"))
        );
    }
}
