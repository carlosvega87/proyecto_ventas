package com.distribuida.Service;

import com.distribuida.dao.MovimientoInventarioRepository;
import com.distribuida.model.MovimientoInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoInventarioServiceImpl implements MovimientoInventarioService {
    @Autowired
    private MovimientoInventarioRepository movimientoInventarioRepository;

    @Override
    public List<MovimientoInventario> findAll() {
        return movimientoInventarioRepository.findAll();
    }
    @Override
    public Optional<MovimientoInventario> findOne(int id) {
        return movimientoInventarioRepository.findById(id);
    }
    @Override
    public MovimientoInventario save(MovimientoInventario movimientoInventario) {
        return movimientoInventarioRepository.save(movimientoInventario);
    }

    @Override
    public MovimientoInventario update(int id, MovimientoInventario movimientoInventario) {
        Optional<MovimientoInventario> movimientoInventarioExistente = movimientoInventarioRepository.findById(id);
        if (movimientoInventarioExistente == null) {
            return null;
        }
        movimientoInventarioExistente.orElse(null).setTipo(movimientoInventario.getTipo());
        movimientoInventarioExistente.orElse(null).setCantidad(movimientoInventario.getCantidad());
        movimientoInventarioExistente.orElse(null).setFechaMovimiento(movimientoInventario.getFechaMovimiento());

        return movimientoInventarioRepository.save(movimientoInventarioExistente.orElse(null));

    }
    @Override
    public void delete(int id) {
        if(movimientoInventarioRepository.existsById(id)){
            movimientoInventarioRepository.deleteById(id);
        }
    }
}
