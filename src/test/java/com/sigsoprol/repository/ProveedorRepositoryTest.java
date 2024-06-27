package com.sigsoprol.repository;

import com.sigsoprol.model.Proveedor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProveedorRepositoryTest {

    @Autowired
    private ProveedorRepository proveedorRepository;

    private Proveedor savedProveedor;

    @BeforeEach
    public void setUp() {
        Proveedor proveedor = new Proveedor();
        proveedor.setNombre("Proveedor Z");
        proveedor.setCorreo("proveedorz@example.com");
        proveedor.setRuc("9876543210");
        proveedor.setDireccion("Calle Zeta 123");
        proveedor.setTelefono("1234567890");
        savedProveedor = proveedorRepository.save(proveedor);
    }

    @Test
    public void testCreateProveedor() {
        assertNotNull(savedProveedor);
        assertNotNull(savedProveedor.getId());
    }

    @Test
    public void testFindProveedorById() {
        Proveedor found = proveedorRepository.findById(savedProveedor.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(savedProveedor.getNombre(), found.getNombre());
    }

    @Test
    public void testUpdateProveedor() {
        savedProveedor.setTelefono("0987654321");
        Proveedor updated = proveedorRepository.save(savedProveedor);
        assertEquals("0987654321", updated.getTelefono());
    }

    @Test
    public void testDeleteProveedor() {
        proveedorRepository.delete(savedProveedor);
        Proveedor deleted = proveedorRepository.findById(savedProveedor.getId()).orElse(null);
        assertNull(deleted);
    }

    @Test
    public void testCountProveedores() {
        long count = proveedorRepository.count();
        assertTrue(count > 0);
    }
}
