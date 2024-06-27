package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.Instant;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String estado;
    private String descripcion;
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    private Empleado empleado;

    @Column(name = "fechacreacion", updatable = false, insertable = false)
    private Instant fechacreacion;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;
}
