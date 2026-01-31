package com.distribuida.Service;

import com.distribuida.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    public List<Categoria> findAll();

    public Optional<Categoria> findOne(int id);

    public Categoria save(Categoria categoria);

    public Categoria uptade(int id, Categoria categoria);

    public void delete(int id);

}
