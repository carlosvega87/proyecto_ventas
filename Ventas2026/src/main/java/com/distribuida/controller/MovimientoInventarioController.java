package com.distribuida.controller;

import com.distribuida.Service.MovimientoInventarioService;
import com.distribuida.Service.MovimientoInventarioService;
import com.distribuida.model.MovimientoInventario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
// mov
@RestController
@RequestMapping("/api/movimientoInventario")
public class MovimientoInventarioController {

    @Autowired
    private MovimientoInventarioService movimientoInventarioService;

    @GetMapping
    public ResponseEntity<List<MovimientoInventario>> findAll(){
        List<MovimientoInventario> movimientoInventario =movimientoInventarioService.findAll();
        return  ResponseEntity.ok(movimientoInventario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoInventario> findOne (@PathVariable int id){
        Optional<MovimientoInventario> movimientoInventario=movimientoInventarioService.findOne(id);
        if(movimientoInventario==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movimientoInventario.orElse(null));
    }

    @PostMapping
    public ResponseEntity<MovimientoInventario> save(@RequestBody MovimientoInventario movimientoInventario){
        MovimientoInventario movimientoInventarioNuevo = movimientoInventarioService.save(movimientoInventario);
        return  ResponseEntity.ok(movimientoInventarioNuevo);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<MovimientoInventario> update(@PathVariable int id , @RequestBody MovimientoInventario movimientoInventario){
        MovimientoInventario MovimientoInventarioActualizado = movimientoInventarioService.update(id ,movimientoInventario);
        if(MovimientoInventarioActualizado==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MovimientoInventarioActualizado);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable int id){
        movimientoInventarioService.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
