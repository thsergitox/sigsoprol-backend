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
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "ordencompra") // Especifica la tabla de la base de datos a la que está mapeada esta entidad
public class OrdenCompra {
    @Id // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica que el valor de la clave primaria es autogenerado
    private Long id;

    private String url;
    private Double precio_total;

    @ManyToOne // Indica una relación muchos-a-uno con la entidad Cotizaciones
    @JoinColumn(name = "id_cotizacion", referencedColumnName = "id") // Especifica la columna de unión para la relación
    private Cotizaciones cotizacion;

    @ManyToOne // Indica una relación muchos-a-uno con la entidad Proveedor
    @JoinColumn(name = "id_proveedor", referencedColumnName = "id") // Especifica la columna de unión para la relación
    private Proveedor proveedor;

    @Column(name = "fechaenvio", updatable = false, insertable = false) // Especifica la columna y sus propiedades
    private Instant fechaenvio;

    @Column(name = "updated_at", updatable = false, insertable = false) // Especifica la columna y sus propiedades
    private Instant updatedAt;
}
