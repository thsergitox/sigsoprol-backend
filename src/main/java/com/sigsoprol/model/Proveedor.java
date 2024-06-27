package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "proveedor")
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String correo;
    private String ruc;
    private String direccion;
    private String telefono;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;

    public Proveedor() {
    }

    public Proveedor(Long id, String nombre, String correo, String ruc, String direccion, String telefono, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.ruc = ruc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
