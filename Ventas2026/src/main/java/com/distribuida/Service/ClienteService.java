package com.distribuida.Service;

import com.distribuida.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    public List<Cliente> findAll();

    public Optional<Cliente> findOne(int id);

    public Cliente save(Cliente cliente);

    public Cliente uptade(int id, Cliente cliente);

    public void delete(int id);
}

