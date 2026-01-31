package com.distribuida.Service;

import com.distribuida.dao.CategoriaRepository;
import com.distribuida.model.Categoria;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findOne(int id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public Categoria uptade(int id, Categoria categoria) {

        Optional<Categoria> categoriaExistente = categoriaRepository.findById(id);

        if (categoriaExistente == null){
            return null;
        }
        categoriaExistente.orElse(null).setNombreCategoria(categoria.getNombreCategoria());




        return categoriaRepository.save(categoriaExistente.orElse(null));
    }

    @Override
    public void delete(int id) {

        if (categoriaRepository.existsById(id)){
            categoriaRepository.deleteById(id);
        }

    }
}
