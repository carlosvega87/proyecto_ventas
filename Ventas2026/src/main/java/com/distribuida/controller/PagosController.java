package com.distribuida.controller;

import com.distribuida.model.Pagos;
import com.distribuida.model.Pedido;
import com.distribuida.service.PagosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pago")
public class PagosController {

    @Autowired
    private PagosService pagosService;

    // LISTAR TODOS
    @GetMapping("/listar")
    public List<Pagos> listar() {
        return pagosService.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pagos> buscarPorId(@PathVariable int id) {
        Optional<Pagos> pagos = pagosService.findOne(id);
        return pagos.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GUARDAR
    @PostMapping("/guardar")
    public Pagos guardar(@RequestBody Pagos pagos) {
        return pagosService.save(pagos);
    }

    // ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pagos> actualizar(@PathVariable int id,
                                             @RequestBody Pagos pagos) {
        Pagos actualizado = pagosService.uptade(id, pagos);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        pagosService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

