package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.Instant;
import lombok.Builder;

@Data
@Entity
@Builder
@Table(name = "cotizaciones")
public class Cotizaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private String estado;
    private Date fechapedido;
    private Double precio_total;

    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
    private Proveedor proveedor;

    @Column(name = "fecharecibimiento", updatable = false, insertable = false)
    private Instant fecharecibimiento;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;

    public Cotizaciones() {
    }

    public Cotizaciones(Long id, String url, String estado, Date fechapedido, Double precio_total, Pedido pedido, Proveedor proveedor, Instant fecharecibimiento, Instant updatedAt) {
        this.id = id;
        this.url = url;
        this.estado = estado;
        this.fechapedido = fechapedido;
        this.precio_total = precio_total;
        this.pedido = pedido;
        this.proveedor = proveedor;
        this.fecharecibimiento = fecharecibimiento;
        this.updatedAt = updatedAt;
    }

}
