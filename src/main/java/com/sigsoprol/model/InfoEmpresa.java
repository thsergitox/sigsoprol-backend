package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "info_empresa")
public class InfoEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String distrito;
    private String direccion;
    private String telefono;
    private String correo;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;

    public InfoEmpresa() {
    }

    public InfoEmpresa(Long id, String nombre, String distrito, String direccion, String telefono, String correo, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.distrito = distrito;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
