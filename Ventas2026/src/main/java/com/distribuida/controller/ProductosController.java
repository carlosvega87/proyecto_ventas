package com.distribuida.controller;

import com.distribuida.Service.ProductosService;
import com.distribuida.model.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductosController {
    @Autowired
    private ProductosService productosService;

    @GetMapping
    public ResponseEntity<List<Productos>> findAll(){
        List<Productos> Productos =productosService.findAll();
        return  ResponseEntity.ok(Productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Productos> findOne (@PathVariable int id){
        Optional<Productos> productos=productosService.findOne(id);
        if(productos==null){
            return  ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productos.orElse(null));
    }

    @PostMapping
    public ResponseEntity<Productos> save(@RequestBody Productos productos){
        Productos ProductosNuevo = productosService.save(productos);
        return  ResponseEntity.ok(ProductosNuevo);
    }
    @PutMapping("/{id}")
    public  ResponseEntity<Productos> update(@PathVariable int id , @RequestBody Productos productos){
        Productos ProductosActualizado = productosService.update(id ,productos);
        if(ProductosActualizado==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ProductosActualizado);
    }
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete (@PathVariable int id){
        productosService.delete(id);
        return  ResponseEntity.noContent().build();
    }



}
