package com.sigsoprol.repository;

import com.sigsoprol.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EmpleadoRepositoryTest {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    private Empleado savedEmpleado;

    @BeforeEach
    public void setUp() {
        Empleado empleado = new Empleado();
        empleado.setNombre("John Doe");
        empleado.setCorreo("john.doe@example.com");
        empleado.setContrasenha("securepassword");
        empleado.setRol("Manager");
        empleado.setEstado("Activo");
        savedEmpleado = empleadoRepository.save(empleado);
    }

    @Test
    public void testCreateEmpleado() {
        assertNotNull(savedEmpleado);
        assertNotNull(savedEmpleado.getId());
    }

    @Test
    public void testFindEmpleadoById() {
        Empleado found = empleadoRepository.findById(savedEmpleado.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(savedEmpleado.getNombre(), found.getNombre());
    }

    @Test
    public void testUpdateEmpleado() {
        savedEmpleado.setEstado("Inactivo");
        Empleado updated = empleadoRepository.save(savedEmpleado);
        assertEquals("Inactivo", updated.getEstado());
    }

    @Test
    public void testDeleteEmpleado() {
        empleadoRepository.delete(savedEmpleado);
        Empleado deleted = empleadoRepository.findById(savedEmpleado.getId()).orElse(null);
        assertNull(deleted);
    }
}
