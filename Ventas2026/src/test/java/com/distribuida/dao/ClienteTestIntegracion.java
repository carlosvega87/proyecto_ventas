package com.distribuida.dao;

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
public class ClienteTestIntegracion {

    @Autowired
    private ClienteRepository clienteRepository;

    @Test
    public void testClienteFindAll(){
        List<Cliente> clientes = clienteRepository.findAll();
        assertNotNull(clientes);
        assertTrue(clientes.size()> 0);
        for (Cliente item: clientes){
            System.out.println(item.toString());
        }
    }

    @Test
    public void testClienteFindOne(){
        Optional<Cliente> cliente = clienteRepository.findById(1);
        assertNotNull(cliente.isPresent());
        assertEquals("Juan", cliente.orElse(null).getNombre());
        assertEquals("Pérez", cliente.orElse(null).getApellido());
        System.out.println(cliente);
    }

    @Test
    public void testClienteSave(){
        Cliente cliente = new Cliente(0, "Anthony2", "Abad2","anthonyabad2@gmail.com", "0999999999","Tumbaco3", new Date());
        Cliente clienteGuardado = clienteRepository.save(cliente);
        assertNotNull(clienteGuardado);
        assertEquals("Anthony2", clienteGuardado.getNombre());
        assertEquals("Abad2", clienteGuardado.getApellido());
    }

    @Test
    public void testClienteActualizar(){
        Optional<Cliente> cliente2 =clienteRepository.findById(53);

        cliente2.orElse(null).setNombre("Daniel");
        cliente2.orElse(null).setApellido("Castillo");
        cliente2.orElse(null).setEmail("daniel2@gmail.com");
        cliente2.orElse(null).setTelefono("0888888888");
        cliente2.orElse(null).setDireccion("Cumbaya33");
        cliente2.orElse(null).setFechaRegistro(new Date());

        clienteRepository.save(cliente2.orElse(null));
    }

    @Test
    public void testClienteBorrar(){
        clienteRepository.deleteById(53);
    }
}
