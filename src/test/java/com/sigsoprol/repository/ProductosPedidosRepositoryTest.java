package com.sigsoprol.repository;

import com.sigsoprol.model.ProductosPedidos;
import com.sigsoprol.model.Producto;
import com.sigsoprol.model.Pedido;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductosPedidosRepositoryTest {

    @Autowired
    private ProductosPedidosRepository productosPedidosRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    private ProductosPedidos savedProductosPedidos;

    @BeforeEach
    public void setUp() {
        Producto producto = new Producto();
        producto.setNombre("Producto Y");
        producto.setPrecio(300.00);
        producto.setCategoria("Accesorios");
        Producto savedProducto = productoRepository.save(producto);

        Pedido pedido = new Pedido();
        pedido.setEstado("Pendiente");
        Pedido savedPedido = pedidoRepository.save(pedido);

        ProductosPedidos productosPedidos = new ProductosPedidos();
        productosPedidos.setProducto(savedProducto);
        productosPedidos.setPedido(savedPedido);
        productosPedidos.setCantidad(10);
        savedProductosPedidos = productosPedidosRepository.save(productosPedidos);
    }

    @Test
    public void testCreateProductosPedidos() {
        assertNotNull(savedProductosPedidos);
        assertNotNull(savedProductosPedidos.getId());
    }

    @Test
    public void testFindProductosPedidosById() {
        ProductosPedidos found = productosPedidosRepository.findById(savedProductosPedidos.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(savedProductosPedidos.getCantidad(), found.getCantidad());
    }

    @Test
    public void testUpdateProductosPedidos() {
        savedProductosPedidos.setCantidad(20);
        ProductosPedidos updated = productosPedidosRepository.save(savedProductosPedidos);
        assertEquals(20, updated.getCantidad());
    }

    @Test
    public void testDeleteProductosPedidos() {
        productosPedidosRepository.delete(savedProductosPedidos);
        ProductosPedidos deleted = productosPedidosRepository.findById(savedProductosPedidos.getId()).orElse(null);
        assertNull(deleted);
    }

    @Test
    public void testCountProductosPedidos() {
        long count = productosPedidosRepository.count();
        assertTrue(count > 0);
    }
}
