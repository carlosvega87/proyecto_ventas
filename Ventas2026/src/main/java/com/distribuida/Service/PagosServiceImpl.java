package com.distribuida.Service;


import com.distribuida.dao.PagosRepository;

import com.distribuida.model.Pagos;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class PagosServiceImpl implements PagosService {
    @Autowired
    private PagosRepository pagosRepository;

    @Override
    public List<Pagos> findAll() {
        return pagosRepository.findAll();
    }

    @Override
    public Optional<Pagos> findOne(int id) {
        return pagosRepository.findById(id);
    }

    @Override
    public Pagos save(Pagos pagos) {
        return pagosRepository.save(pagos);
    }

    @Override
    public Pagos uptade(int id, Pagos pagos) {

        Optional<Pagos> pagos1Existente = pagosRepository.findById(id);

        if (pagos1Existente == null){
            return null;
        }
        pagos1Existente.orElse(null).setMetodoPago(pagos.getMetodoPago());
        pagos1Existente.orElse(null).setFechaPago(pagos.getFechaPago());


        return pagosRepository.save(pagos1Existente.orElse(null));
    }

    @Override
    public void delete(int id) {

        if (pagosRepository.existsById(id)){
            pagosRepository.deleteById(id);
        }

    }
}
