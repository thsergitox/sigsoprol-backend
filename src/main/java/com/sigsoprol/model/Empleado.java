package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.Instant;

@Data
@Entity
@Table(name = "empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String apellido;
    private String nombre;
    private String correo;
    private String contrasenha;
    private String rol;
    private String estado;

    @Column(name = "fechadeIngreso", updatable = false, insertable = false)
    private Instant fechadeIngreso;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;
}
