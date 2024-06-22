/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sigsoprol.repository;

import com.sigsoprol.model.OrdenCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un repositorio de Spring, que proporciona operaciones CRUD para la entidad OrdenCompra
public interface OrdenCompraRepository extends JpaRepository<OrdenCompra, Long> {
    // Extiende JpaRepository, proporcionando métodos CRUD y de paginación para OrdenCompra
    // El parámetro OrdenCompra indica la entidad con la que trabaja
    // El parámetro Long indica el tipo de la clave primaria de la entidad OrdenCompra
}
