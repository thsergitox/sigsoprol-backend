package com.sigsoprol.repository;

import com.sigsoprol.model.Pedido;
import com.sigsoprol.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PedidoRepositoryTest {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private Pedido savedPedido;

    @BeforeEach
    public void setUp() {
        Empleado empleado = new Empleado();
        empleado.setNombre("Empleado Test");
        empleado.setCorreo("empleado@example.com");
        Empleado savedEmpleado = empleadoRepository.save(empleado);

        Pedido pedido = new Pedido();
        pedido.setEstado("Pendiente");
        pedido.setEmpleado(savedEmpleado);
        savedPedido = pedidoRepository.save(pedido);
    }

    @Test
    public void testCreatePedido() {
        assertNotNull(savedPedido);
        assertNotNull(savedPedido.getId());
    }

    @Test
    public void testFindPedidoById() {
        Pedido found = pedidoRepository.findById(savedPedido.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(savedPedido.getEstado(), found.getEstado());
    }

    @Test
    public void testUpdatePedido() {
        savedPedido.setEstado("Aprobado");
        Pedido updated = pedidoRepository.save(savedPedido);
        assertEquals("Aprobado", updated.getEstado());
    }

    @Test
    public void testDeletePedido() {
        pedidoRepository.delete(savedPedido);
        Pedido deleted = pedidoRepository.findById(savedPedido.getId()).orElse(null);
        assertNull(deleted);
    }

    @Test
    public void testCountPedidos() {
        long count = pedidoRepository.count();
        assertTrue(count > 0);
    }
}
