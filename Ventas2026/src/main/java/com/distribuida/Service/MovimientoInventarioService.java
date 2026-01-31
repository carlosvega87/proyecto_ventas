package com.distribuida.Service;

import com.distribuida.model.MovimientoInventario;

import java.util.List;
import java.util.Optional;

public interface MovimientoInventarioService {
    public List<MovimientoInventario> findAll();
    public Optional<MovimientoInventario> findOne(int id);
    public MovimientoInventario save( MovimientoInventario movimientoInventario);
    public MovimientoInventario update (int id,MovimientoInventario movimientoInventario);
    public void delete (int id);
}
