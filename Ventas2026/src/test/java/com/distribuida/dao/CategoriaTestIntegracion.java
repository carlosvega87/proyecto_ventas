package com.distribuida.dao;

import com.distribuida.model.Categoria;
import com.distribuida.model.Cliente;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@Rollback(value = false)
public class CategoriaTestIntegracion {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void testCategoriaFindAll(){
        List<Categoria> categorias = categoriaRepository.findAll();
        assertNotNull(categorias);
        assertTrue(categorias.size()> 0);
        for (Categoria item: categorias){
            System.out.println(item.toString());
        }
    }

    @Test
    public void testCategoriaFindOne(){
        Optional<Categoria> categoria = categoriaRepository.findById(1);
        assertNotNull(categoria.isPresent());
        assertEquals("Electronica", categoria.orElse(null).getNombreCategoria());
        System.out.println(categoria);
    }

    @Test
    public void testCategoriaSave(){
        Categoria categoria = new Categoria(0,"Plastico");
        Categoria categoriaGuardada = categoriaRepository.save(categoria);
        assertNotNull(categoriaGuardada);
        assertEquals("Plastico", categoriaGuardada.getNombreCategoria());
    }

    @Test
    public void testCategoriaActualizar(){
        Optional<Categoria> categoria2 =categoriaRepository.findById(11);

        categoria2.orElse(null).setNombreCategoria("Plastico22");


        categoriaRepository.save(categoria2.orElse(null));
    }
    @Test
    public void testCategoriaBorrar(){
        categoriaRepository.deleteById(11);
    }
}
