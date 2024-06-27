package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "productoalmacen")
public class ProductoAlmacen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;

    @OneToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;

    public ProductoAlmacen() {
    }

    public ProductoAlmacen(Long id, Integer cantidad, Instant createdAt, Instant updatedAt, Producto producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.producto = producto;
    }

}
