package com.sigsoprol.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sigsoprol.model.Cotizaciones;
import com.sigsoprol.service.CotizacionesService;
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

@WebMvcTest(controllers = CotizacionesController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CotizacionesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CotizacionesService cotizacionesService;

    @Autowired
    private ObjectMapper objectMapper;

    private Cotizaciones cotizacion1, cotizacion2;

    @BeforeEach
    public void init() {
        cotizacion1 = Cotizaciones.builder()
                .url("http://example.com")
                .estado("activo")
                .build();

        cotizacion2 = Cotizaciones.builder()
                .url("http://example.com/2")
                .estado("pendiente")
                .build();
    }

    @Test
    public void CotizacionesController_Insert() throws Exception {
        given(cotizacionesService.save(ArgumentMatchers.any())).willAnswer(invocation -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/api/cotizaciones/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cotizacion1)));

        response.andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado", CoreMatchers.is(cotizacion1.getEstado())));
    }

    @Test
    public void CotizacionesController_Listar() throws Exception {
        when(cotizacionesService.findAll()).thenReturn(Arrays.asList(cotizacion1, cotizacion2));

        ResultActions response = mockMvc.perform(get("/api/cotizaciones/all")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(2)))
                .andExpect(jsonPath("$[0].estado", CoreMatchers.is(cotizacion1.getEstado())));
    }

    @Test
    public void CotizacionesController_FindById() throws Exception {
        when(cotizacionesService.findById(1L)).thenReturn(Optional.of(cotizacion1));

        ResultActions response = mockMvc.perform(get("/api/cotizaciones/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.estado", CoreMatchers.is(cotizacion1.getEstado())));
    }

    @Test
    public void CotizacionesController_Delete() throws Exception {
        doNothing().when(cotizacionesService).deleteById(1L);

        ResultActions response = mockMvc.perform(delete("/api/cotizaciones/delete/1")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(status().isNoContent());
    }
}
