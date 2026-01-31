package com.distribuida.Service;

import com.distribuida.model.Productos;

import java.util.List;
import java.util.Optional;

public interface ProductosService {
    public List<Productos> findAll();
    public Optional<Productos> findOne(int id);
    public Productos save( Productos productos);
    public Productos update (int id,Productos productos);
    public void delete (int id);
}
