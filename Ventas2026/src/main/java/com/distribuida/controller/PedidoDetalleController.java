package com.distribuida.controller;

import com.distribuida.Service.PedidoDetalleService;
import com.distribuida.model.PedidoDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido-detalle")
public class PedidoDetalleController {

    @Autowired
    private PedidoDetalleService pedidoDetalleService;

    // LISTAR TODOS
    @GetMapping("/listar")
    public List<PedidoDetalle> listar() {
        return pedidoDetalleService.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<PedidoDetalle> buscarPorId(@PathVariable int id) {
        Optional<PedidoDetalle> detalle = pedidoDetalleService.findOne(id);
        return detalle.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GUARDAR
    @PostMapping("/guardar")
    public PedidoDetalle guardar(@RequestBody PedidoDetalle detalle) {
        return pedidoDetalleService.save(detalle);
    }

    // ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PedidoDetalle> actualizar(@PathVariable int id,
                                                    @RequestBody PedidoDetalle detalle) {
        PedidoDetalle actualizado = pedidoDetalleService.update(id, detalle);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        pedidoDetalleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
