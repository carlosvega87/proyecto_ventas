package com.distribuida.Service;

import com.distribuida.dao.ClienteRepository;
import com.distribuida.model.Cliente;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Optional<Cliente> findOne(int id) {
        return clienteRepository.findById(id);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente uptade(int id, Cliente cliente) {

        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente == null){
            return null;
        }
        clienteExistente.orElse(null).setNombre(cliente.getNombre());
        clienteExistente.orElse(null).setApellido(cliente.getApellido());
        clienteExistente.orElse(null).setEmail(cliente.getEmail());
        clienteExistente.orElse(null).setTelefono(cliente.getTelefono());
        clienteExistente.orElse(null).setDireccion(cliente.getDireccion());
        clienteExistente.orElse(null).setFechaRegistro(cliente.getFechaRegistro());

        return clienteRepository.save(clienteExistente.orElse(null));
    }

    @Override
    public void delete(int id) {

        if (clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
        }

    }
}
