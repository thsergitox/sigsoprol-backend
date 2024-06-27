package com.sigsoprol.repository;

import com.sigsoprol.model.Cotizaciones;
import com.sigsoprol.model.Pedido;
import com.sigsoprol.model.Proveedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2, replace = Replace.NONE)
public class CotizacionesRepositoryTest {

    @Autowired
    private CotizacionesRepository cotizacionesRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProveedorRepository proveedorRepository;

    private Cotizaciones savedCotizacion;

    @BeforeEach
    public void setUp() {
        // Creamos y guardamos las entidades relacionadas primero
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Proveedor Test");
        proveedor.setCorreo("contacto@test.com");
        proveedor.setRuc("1234567890");
        proveedor.setDireccion("Calle Falsa 123");
        proveedor.setTelefono("987654321");
        Proveedor savedProveedor = proveedorRepository.save(proveedor);

        Pedido pedido = new Pedido();
        pedido.setEstado("Pendiente");
        Pedido savedPedido = pedidoRepository.save(pedido);

        // Ahora creamos la cotización con las entidades relacionadas ya guardadas
        Cotizaciones cotizacion = Cotizaciones.builder()
            .url("http://example.com")
            .estado("activo")
            .fechapedido(new Date(System.currentTimeMillis()))
            .precio_total(1234.56)
            .pedido(savedPedido)
            .proveedor(savedProveedor)
            .build();

        savedCotizacion = cotizacionesRepository.save(cotizacion);
    }

    @Test
    public void testCreateCotizacion() {
        assertNotNull(savedCotizacion);
        assertNotNull(savedCotizacion.getId());
    }

    @Test
    public void testFindCotizacionById() {
        Optional<Cotizaciones> found = cotizacionesRepository.findById(savedCotizacion.getId());
        assertTrue(found.isPresent());
        assertEquals(savedCotizacion.getId(), found.get().getId());
    }

    @Test
    public void testUpdateCotizacion() {
        savedCotizacion.setEstado("cancelado");
        Cotizaciones updated = cotizacionesRepository.save(savedCotizacion);
        assertEquals("cancelado", updated.getEstado());
    }

    @Test
    public void testDeleteCotizacion() {
        cotizacionesRepository.delete(savedCotizacion);
        Optional<Cotizaciones> deleted = cotizacionesRepository.findById(savedCotizacion.getId());
        assertFalse(deleted.isPresent());
    }

    @Test
    public void testListCotizaciones() {
        List<Cotizaciones> cotizaciones = cotizacionesRepository.findAll();
        assertFalse(cotizaciones.isEmpty());
        assertTrue(cotizaciones.contains(savedCotizacion));
    }
}
