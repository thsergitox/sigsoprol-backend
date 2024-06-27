package com.sigsoprol.repository;

import com.sigsoprol.model.ProductoAlmacen;
import com.sigsoprol.model.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoAlmacenRepositoryTest {

    @Autowired
    private ProductoAlmacenRepository productoAlmacenRepository;

    @Autowired
    private ProductoRepository productoRepository;

    private ProductoAlmacen savedProductoAlmacen;

    @BeforeEach
    public void setUp() {
        Producto producto = new Producto();
        producto.setNombre("Producto Test");
        producto.setPrecio(100.00);
        producto.setCategoria("Categoría Test");
        Producto savedProducto = productoRepository.save(producto);

        ProductoAlmacen productoAlmacen = new ProductoAlmacen();
        productoAlmacen.setId(1L); // Asignar manualmente el ID
        productoAlmacen.setProducto(savedProducto);
        productoAlmacen.setCantidad(50);
        savedProductoAlmacen = productoAlmacenRepository.save(productoAlmacen);
    }

    @Test
    public void testCreateProductoAlmacen() {
        assertNotNull(savedProductoAlmacen);
        assertNotNull(savedProductoAlmacen.getId());
    }

//    @Test
//    public void testFindProductoAlmacenById() {
//        ProductoAlmacen found = productoAlmacenRepository.findById(savedProductoAlmacen.getId()).orElse(null);
//        assertNotNull(found);
//        assertEquals(savedProductoAlmacen.getCantidad(), found.getCantidad());
//    }
//
//    @Test
//    public void testUpdateProductoAlmacen() {
//        savedProductoAlmacen.setCantidad(75);
//        ProductoAlmacen updated = productoAlmacenRepository.save(savedProductoAlmacen);
//        assertEquals(75, updated.getCantidad());
//    }
//
//    @Test
//    public void testDeleteProductoAlmacen() {
//        productoAlmacenRepository.delete(savedProductoAlmacen);
//        ProductoAlmacen deleted = productoAlmacenRepository.findById(savedProductoAlmacen.getId()).orElse(null);
//        assertNull(deleted);
//    }
//
//    @Test
//    public void testCountProductoAlmacen() {
//        long count = productoAlmacenRepository.count();
//        assertTrue(count > 0);
//    }
}
