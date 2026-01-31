package com.distribuida.Service;


import com.distribuida.model.Pagos;

import java.util.List;
import java.util.Optional;

public interface PagosService {
    public List<Pagos> findAll();

    public Optional<Pagos> findOne(int id);

    public Pagos save(Pagos pagos);

    public Pagos uptade(int id, Pagos pagos);

    public void delete(int id);
}
