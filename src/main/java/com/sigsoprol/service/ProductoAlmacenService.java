package com.sigsoprol.service;

import com.sigsoprol.model.ProductoAlmacen;
import com.sigsoprol.repository.ProductoAlmacenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoAlmacenService {

    @Autowired
    private ProductoAlmacenRepository productoAlmacenRepository;

    public List<ProductoAlmacen> findAll() {
        return productoAlmacenRepository.findAll();
    }

    public Optional<ProductoAlmacen> findById(Long id) {
        return productoAlmacenRepository.findById(id);
    }

    public ProductoAlmacen save(ProductoAlmacen productoAlmacen) {
        return productoAlmacenRepository.save(productoAlmacen);
    }

    public void deleteById(Long id) {
        productoAlmacenRepository.deleteById(id);
    }
}
