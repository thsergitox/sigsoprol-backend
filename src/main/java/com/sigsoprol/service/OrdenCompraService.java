package com.sigsoprol.service;

import com.sigsoprol.model.OrdenCompra;
import com.sigsoprol.repository.OrdenCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indica que esta clase es un servicio de Spring, que contiene la lógica de negocio
public class OrdenCompraService {

    @Autowired // Inyecta una instancia de OrdenCompraRepository
    private OrdenCompraRepository ordenCompraRepository;

    // Método para obtener todas las órdenes de compra
    public List<OrdenCompra> findAll() {
        return ordenCompraRepository.findAll();
    }

    // Método para obtener una orden de compra por su ID
    public Optional<OrdenCompra> findById(Long id) {
        return ordenCompraRepository.findById(id);
    }

    // Método para guardar una nueva orden de compra o actualizar una existente
    public OrdenCompra save(OrdenCompra ordenCompra) {
        return ordenCompraRepository.save(ordenCompra);
    }

    // Método para eliminar una orden de compra por su ID
    public void deleteById(Long id) {
        ordenCompraRepository.deleteById(id);
    }
}
