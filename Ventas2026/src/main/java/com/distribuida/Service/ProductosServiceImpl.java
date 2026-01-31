package com.distribuida.Service;

import com.distribuida.dao.ProductosRepository;
import com.distribuida.model.Productos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductosServiceImpl implements ProductosService {

        @Autowired
        private ProductosRepository productosRepository;

        @Override
        public List<Productos> findAll() {
            return productosRepository.findAll();
        }
        @Override
        public Optional<Productos> findOne(int id) {
            return productosRepository.findById(id);
        }
        @Override
        public Productos save(Productos productos) {
            return productosRepository.save(productos);
        }

    @Override
        public Productos update(int id, Productos productos) {
            Optional<Productos> productoExistente = productosRepository.findById(id);
            if (productoExistente == null) {
                return null;
            }
            productoExistente.orElse(null).setNombre(productos.getNombre());
            productoExistente.orElse(null).setPrecio(productos.getPrecio());
            productoExistente.orElse(null).setStock(productos.getStock());
            productoExistente.orElse(null).setFechaRegistro(productos.getFechaRegistro());
            productoExistente.orElse(null).setEstado(productos.getEstado());

            return productosRepository.save(productoExistente.orElse(null));

        }
        @Override
        public void delete(int id) {
            if(productosRepository.existsById(id)){
                productosRepository.deleteById(id);
            }
        }
}
