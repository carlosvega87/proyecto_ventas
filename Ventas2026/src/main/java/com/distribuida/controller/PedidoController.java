package com.distribuida.controller;

import com.distribuida.Service.PedidoService;
import com.distribuida.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // LISTAR TODOS
    @GetMapping("/listar")
    public List<Pedido> listar() {
        return pedidoService.findAll();
    }

    // BUSCAR POR ID
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable int id) {
        Optional<Pedido> pedido = pedidoService.findOne(id);
        return pedido.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GUARDAR
    @PostMapping("/guardar")
    public Pedido guardar(@RequestBody Pedido pedido) {
        return pedidoService.save(pedido);
    }

    // ACTUALIZAR
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable int id,
                                             @RequestBody Pedido pedido) {
        Pedido actualizado = pedidoService.update(id, pedido);
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actualizado);
    }

    // ELIMINAR
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
