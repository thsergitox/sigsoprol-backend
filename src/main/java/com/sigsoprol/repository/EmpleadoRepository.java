package com.sigsoprol.repository;

import com.sigsoprol.model.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends  JpaRepository<Empleado, Long> {
}
