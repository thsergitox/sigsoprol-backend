
package com.sigsoprol.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

//anotaciones:
@DataJpaTest
@AutoConfigureTestDatabase (connection=EmbeddedDatabaseConnection. H2)
public class CotizacionesRepositoryTest {
    
    public CotizacionesRepositoryTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
}
