package com.sigsoprol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigsoprol.model.Empleado;
import com.sigsoprol.service.EmpleadoService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = EmpleadoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Empleado empleado1, empleado2;

    @BeforeEach
    public void init() {
        empleado1 = Empleado.builder()
                .nombre("John Doe")
                .correo("john.doe@example.com")
                .contrasenha("securepassword")
                .rol("Manager")
                .estado("Activo")
                .build();

        empleado2 = Empleado.builder()
                .nombre("Jane Smith")
                .correo("jane.smith@example.com")
                .contrasenha("anotherpassword")
                .rol("Employee")
                .estado("Inactivo")
                .build();
    }

    @Test
    public void EmpleadoController_Insert() throws Exception {
        given(empleadoService.save(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/empleados/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleado1)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre", CoreMatchers.is(empleado1.getNombre())));
    }

    @Test
    public void EmpleadoController_Listar() throws Exception {
        when(empleadoService.findAll()).thenReturn(Arrays.asList(empleado1, empleado2));

        ResultActions response = mockMvc.perform(get("/api/empleados/all")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(2)))
                .andExpect(jsonPath("$[0].nombre", CoreMatchers.is(empleado1.getNombre())));
    }

    @Test
    public void EmpleadoController_FindById() throws Exception {
        when(empleadoService.findById(1L)).thenReturn(Optional.of(empleado1));

        ResultActions response = mockMvc.perform(get("/api/empleados/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", CoreMatchers.is(empleado1.getNombre())));
    }

    @Test
    public void EmpleadoController_Delete() throws Exception {
        doNothing().when(empleadoService).deleteById(1L);

        ResultActions response = mockMvc.perform(delete("/api/empleados/delete/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
