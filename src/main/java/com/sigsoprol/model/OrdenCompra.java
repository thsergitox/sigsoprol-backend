package com.sigsoprol.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.Data;

@Data
@Entity
@Table(name = "ordencompra")
public class OrdenCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;
    private Double precio_total;

    @ManyToOne
    @JoinColumn(name = "id_cotizacion", referencedColumnName = "id")
    private Cotizaciones cotizacion;

    @ManyToOne
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id")
    private Proveedor proveedor;

    @Column(name = "fechaenvio", updatable = false, insertable = false)
    private Instant fechaenvio;

    @Column(name = "updated_at", updatable = false, insertable = false)
    private Instant updatedAt;
}
