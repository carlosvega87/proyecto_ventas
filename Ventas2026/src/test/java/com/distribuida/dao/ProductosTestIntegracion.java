package com.distribuida.dao;

import com.distribuida.model.Categoria;
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
public class ProductosTestIntegracion {
    @Autowired
    private ProductosRepository productosRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Test
    public void testProductosFindAll() {
        List<Productos> productos = productosRepository.findAll();
        assertNotNull(productos);
        assertTrue(productos.size() > 0);
        for (Productos item : productos) {
            System.out.println(item.toString());
        }
    }
    @Test
    public void testProductosFindOne(){
        Optional<Productos> productos = productosRepository.findById(17);
        assertTrue(productos.isPresent());
        assertEquals("Nintendo Switch OLED",productos.orElse(null).getNombre());
        assertEquals(349.9,productos.orElse(null).getPrecio());

        System.out.println(productos);
    }
    @Test
    public void testProductoSave(){
        Optional<Categoria> categoria1= categoriaRepository.findById(1);
        assertTrue(categoria1.isPresent());

        Productos productos=new Productos();
        productos.setIdProducto(0);
        productos.setNombre("Play Station 4");
        productos.setPrecio(120.50);
        productos.setStock(10);
        productos.setFechaRegistro("19/1/2025");
        productos.setEstado("Disponible");
        productos.setCategoria(categoria1.orElse(null));

      Productos productos1Guardada= productosRepository.save(productos);
        assertNotNull(productos1Guardada);
        assertEquals("Play Station 4",productos1Guardada.getNombre());
        assertEquals(120.50, productos1Guardada.getPrecio());
        assertEquals(10, productos1Guardada.getStock());
        assertEquals("19/1/2025", productos1Guardada.getFechaRegistro());
        assertEquals("Disponible", productos1Guardada.getEstado());
        assertEquals(120.50, productos1Guardada.getPrecio());
    }
    @Test
    public void testFacturaDetalleUpdate() {
        Optional<Categoria> categoria = categoriaRepository.findById(2);

        assertTrue(categoria.isPresent());

        Optional<Productos> productos = productosRepository.findById(48);

        assertTrue((productos.isPresent()));


        productos.orElse(null).setNombre("Play Station 1");
        productos.orElse(null).setPrecio(350.00);
        productos.orElse(null).setStock(20);
        productos.orElse(null).setFechaRegistro("2/1/2025");
        productos.orElse(null).setEstado("Disponible");
        productos.orElse(null).setCategoria(categoria.orElse(null));

        Productos productos1Actualizada = productosRepository.save(productos.orElse(null));
        assertNotNull(productos1Actualizada);
        assertEquals("Play Station 1", productos1Actualizada.getNombre());
        assertEquals(350.00, productos1Actualizada.getPrecio());
        assertEquals(20, productos1Actualizada.getStock());
        assertEquals("2/1/2025", productos1Actualizada.getFechaRegistro());
        assertEquals("Disponible", productos1Actualizada.getEstado());
    }
    @Test
    public void testProductosDelete(){
        if (productosRepository.existsById(48)){
            productosRepository.deleteById(48);
        }
        assertFalse(productosRepository.existsById(48));
    }


}
