package com.distribuida.dao;

import com.distribuida.model.MovimientoInventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovimientoInventarioRepository extends JpaRepository<MovimientoInventario,Integer> {
    static Optional<MovimientoInventario> findbyId(int id) {
        return null;
    }
}
