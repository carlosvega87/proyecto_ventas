package com.distribuida.dao;

import com.distribuida.model.Categoria;
import com.distribuida.model.MovimientoInventario;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value= false)
public class MovimientoInventarioTestIntegracion {
    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    @Autowired
    private ProductosRepository productosRepository;

    @Test
    public void testMovimientoInventarioFindAll() {
        List<MovimientoInventario> movimientoInventario = movimientoInventarioRepository.findAll();
        assertNotNull(movimientoInventario);
        assertTrue(movimientoInventario.size() > 0);
        for (MovimientoInventario item : movimientoInventario) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void testMovimientoInventarioFindOne(){
        Optional<MovimientoInventario> movimientoInventario = movimientoInventarioRepository.findById(10);
        assertTrue(movimientoInventario.isPresent());
        assertEquals("SALIDA",movimientoInventario.orElse(null).getTipo());
        assertEquals(10,movimientoInventario.orElse(null).getCantidad());

        System.out.println(movimientoInventario);
    }
    @Test
    public void testMovimientoInventarioSave(){
        Optional<Productos> productos= productosRepository.findById(1);
        assertTrue(productos.isPresent());

        MovimientoInventario movimientoInventario=new MovimientoInventario();
        movimientoInventario.setTipo("ENTRADA");
        movimientoInventario.setCantidad(50);
        movimientoInventario.setFechaMovimiento("7/12/2025");
        movimientoInventario.setProductos(productos.orElse(null));

        MovimientoInventario movimientoInventario1Guardada= movimientoInventarioRepository.save(movimientoInventario);
        assertNotNull(movimientoInventario1Guardada);
        assertEquals("ENTRADA",movimientoInventario1Guardada.getTipo());
        assertEquals(50,movimientoInventario1Guardada.getCantidad());
        assertEquals("7/12/2025",movimientoInventario1Guardada.getFechaMovimiento());
    }
    @Test
    public void testMovimientoInventarioUpdate() {
        Optional<Productos> productos= productosRepository.findById(2);

        assertTrue(productos.isPresent());

        Optional<MovimientoInventario> movimientoInventario = movimientoInventarioRepository.findById(52);

        assertTrue((movimientoInventario.isPresent()));

        movimientoInventario.orElse(null).setTipo("Salida");
        movimientoInventario.orElse(null).setCantidad(20);
        movimientoInventario.orElse(null).setFechaMovimiento("8/12/2025");
        movimientoInventario.orElse(null).setProductos(productos.orElse(null));

        MovimientoInventario movimientoInventario1Actualizada = movimientoInventarioRepository.save(movimientoInventario.orElse(null));
        assertNotNull(movimientoInventario1Actualizada);
        assertEquals("Salida",movimientoInventario1Actualizada.getTipo());
        assertEquals(20,movimientoInventario1Actualizada.getCantidad());
        assertEquals("8/12/2025",movimientoInventario1Actualizada.getFechaMovimiento());
    }
    @Test
    public void testMovimientoInventarioDelete(){
        if (movimientoInventarioRepository.existsById(52)){
            movimientoInventarioRepository.deleteById(52);
        }
        assertFalse(movimientoInventarioRepository.existsById(52));
    }



}
