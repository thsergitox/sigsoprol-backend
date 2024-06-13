package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;

@Data
@Entity
@Table(name = "productoalmacen")
public class ProductoAlmacen {
    @Id
    private Long id;

    private Integer cantidad;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;

    @OneToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;
}
