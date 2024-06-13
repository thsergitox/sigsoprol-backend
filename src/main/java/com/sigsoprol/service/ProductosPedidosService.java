package com.sigsoprol.service;

import com.sigsoprol.model.ProductosPedidos;
import com.sigsoprol.repository.ProductosPedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductosPedidosService {

    @Autowired
    private ProductosPedidosRepository productosPedidosRepository;

    public List<ProductosPedidos> findAll() {
        return productosPedidosRepository.findAll();
    }

    public Optional<ProductosPedidos> findById(Long id) {
        return productosPedidosRepository.findById(id);
    }

    public ProductosPedidos save(ProductosPedidos productosPedidos) {
        return productosPedidosRepository.save(productosPedidos);
    }

    public void deleteById(Long id) {
        productosPedidosRepository.deleteById(id);
    }
}
