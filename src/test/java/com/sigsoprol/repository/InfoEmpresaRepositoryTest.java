package com.sigsoprol.repository;

import com.sigsoprol.model.InfoEmpresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class InfoEmpresaRepositoryTest {

    @Autowired
    private InfoEmpresaRepository infoEmpresaRepository;

    private InfoEmpresa savedInfoEmpresa;

    @BeforeEach
    public void setUp() {
        InfoEmpresa infoEmpresa = new InfoEmpresa();
        infoEmpresa.setNombre("SigSoprol Ltd.");
        infoEmpresa.setDistrito("San José");
        infoEmpresa.setDireccion("Calle Principal 123");
        infoEmpresa.setTelefono("9876543210");
        infoEmpresa.setCorreo("contact@sigsoprol.com");
        savedInfoEmpresa = infoEmpresaRepository.save(infoEmpresa);
    }

    @Test
    public void testCreateInfoEmpresa() {
        assertNotNull(savedInfoEmpresa);
        assertNotNull(savedInfoEmpresa.getId());
    }

    @Test
    public void testFindInfoEmpresaById() {
        InfoEmpresa found = infoEmpresaRepository.findById(savedInfoEmpresa.getId()).orElse(null);
        assertNotNull(found);
        assertEquals(savedInfoEmpresa.getNombre(), found.getNombre());
    }

    @Test
    public void testUpdateInfoEmpresa() {
        savedInfoEmpresa.setTelefono("1234567890");
        InfoEmpresa updated = infoEmpresaRepository.save(savedInfoEmpresa);
        assertEquals("1234567890", updated.getTelefono());
    }

    @Test
    public void testDeleteInfoEmpresa() {
        infoEmpresaRepository.delete(savedInfoEmpresa);
        InfoEmpresa deleted = infoEmpresaRepository.findById(savedInfoEmpresa.getId()).orElse(null);
        assertNull(deleted);
    }
}
