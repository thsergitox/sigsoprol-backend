package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.Instant;
import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "productospedidos")
public class ProductosPedidos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;
    private Double total;

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    private Producto producto;

    @Column(name = "created_at", updatable = false, insertable = false)
    private Instant createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;

    public ProductosPedidos() {
    }

    public ProductosPedidos(Long id, Integer cantidad, Double total, Pedido pedido, Producto producto, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.cantidad = cantidad;
        this.total = total;
        this.pedido = pedido;
        this.producto = producto;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
