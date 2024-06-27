package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.Instant;
import lombok.Builder;

@Data
@Entity
@Builder
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

    public Empleado() {
    }

    public Empleado(Long id, String apellido, String nombre, String correo, String contrasenha, String rol, String estado, Instant fechadeIngreso, Instant updatedAt) {
        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenha = contrasenha;
        this.rol = rol;
        this.estado = estado;
        this.fechadeIngreso = fechadeIngreso;
        this.updatedAt = updatedAt;
    }

}
