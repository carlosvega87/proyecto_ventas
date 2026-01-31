package com.distribuida.dao;

import com.distribuida.model.Pagos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagosRepository extends JpaRepository<Pagos, Integer> {

}
