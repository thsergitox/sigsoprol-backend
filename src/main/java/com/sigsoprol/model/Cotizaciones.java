package com.sigsoprol.model;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.time.Instant;

@Data
@Entity
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
}
