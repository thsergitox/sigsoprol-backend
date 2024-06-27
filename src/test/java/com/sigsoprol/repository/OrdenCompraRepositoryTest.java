package com.sigsoprol.repository;

import com.sigsoprol.model.OrdenCompra;
import com.sigsoprol.model.Cotizaciones;
import com.sigsoprol.model.Proveedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class OrdenCompraRepositoryTest {

    @Autowired
    private OrdenCompraRepository ordenCompraRepository;

    @Autowired
    private CotizacionesRepository cotizacionesRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

    private OrdenCompra savedOrdenCompra;

    @BeforeEach
    public void setUp() {
        // Preparación de entidades relacionadas
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Proveedor Test");
        proveedor.setCorreo("proveedor@example.com");
        Proveedor savedProveedor = proveedorRepository.save(proveedor);

        Cotizaciones cotizacion = new Cotizaciones();
        cotizacion.setEstado("Aprobado");
        cotizacion.setProveedor(savedProveedor);
        Cotizaciones savedCotizacion = cotizacionesRepository.save(cotizacion);

        OrdenCompra ordenCompra = new OrdenCompra();
        ordenCompra.setUrl("http://example.com/order");
        ordenCompra.setCotizacion(savedCotizacion);
        ordenCompra.setProveedor(savedProveedor);
        savedOrdenCompra = ordenCompraRepository.save(ordenCompra);
    }

    @Test
    public void testCreateOrdenCompra() {
        assertNotNull(savedOrdenCompra);
        assertNotNull(savedOrdenCompra.getId());
    }

    @Test
    public void testFindOrdenCompraById() {
        OrdenCompra found = ordenCompraRepository.findById(savedOrdenCompra.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(savedOrdenCompra.getUrl(), found.getUrl());
    }

    @Test
    public void testUpdateOrdenCompra() {
        savedOrdenCompra.setUrl("http://example.com/updated_order");
        OrdenCompra updated = ordenCompraRepository.save(savedOrdenCompra);
        assertEquals("http://example.com/updated_order", updated.getUrl());
    }

    @Test
    public void testDeleteOrdenCompra() {
        ordenCompraRepository.delete(savedOrdenCompra);
        OrdenCompra deleted = ordenCompraRepository.findById(savedOrdenCompra.getId()).orElse(null);
        assertNull(deleted);
    }

    @Test
    public void testCountOrdenCompra() {
        long count = ordenCompraRepository.count();
        assertTrue(count > 0);
    }
}
