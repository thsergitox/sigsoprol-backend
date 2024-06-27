package com.sigsoprol.repository;

import com.sigsoprol.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    private Producto savedProducto;

    @BeforeEach
    public void setUp() {
        Producto producto = new Producto();
        producto.setNombre("Producto X");
        producto.setPrecio(200.00);
        producto.setCategoria("Electrónica");
        savedProducto = productoRepository.save(producto);
    }

    @Test
    public void testCreateProducto() {
        assertNotNull(savedProducto);
        assertNotNull(savedProducto.getId());
    }

    @Test
    public void testFindProductoById() {
        Producto found = productoRepository.findById(savedProducto.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(savedProducto.getNombre(), found.getNombre());
    }

    @Test
    public void testUpdateProducto() {
        savedProducto.setPrecio(250.00);
        Producto updated = productoRepository.save(savedProducto);
        assertEquals(250.00, updated.getPrecio());
    }

    @Test
    public void testDeleteProducto() {
        productoRepository.delete(savedProducto);
        Producto deleted = productoRepository.findById(savedProducto.getId()).orElse(null);
        assertNull(deleted);
    }

    @Test
    public void testCountProductos() {
        long count = productoRepository.count();
        assertTrue(count > 0);
    }
}
