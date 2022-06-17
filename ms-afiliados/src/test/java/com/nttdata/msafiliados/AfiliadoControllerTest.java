package com.nttdata.msafiliados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nttdata.msafiliados.business.service.AfiliadoService;
import com.nttdata.msafiliados.domain.dto.AfiliadoDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AfiliadoControllerTest extends AbstractTest{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AfiliadoService afiliadoService;

    @Test
    public void shouldListAfiliados() throws Exception {
        when(afiliadoService.getAll()).thenReturn(getAnyListAfiliadosDto());

        mockMvc.perform(get("/api/v1/afiliado")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].numeroIdentificacion", is("1000185557")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAfiliadoById() throws Exception {
        when(afiliadoService.findById(1)).thenReturn(getAnyAfiliadoDto());

        mockMvc.perform(get("/api/v1/afiliado/1")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.numeroIdentificacion", is("1000185557")))
                .andExpect(jsonPath("$.fechaCreacion", is("2020-11-17 16:00:00.000")))
                .andExpect(jsonPath("$.afiliadoId", is(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAfiliadoByIdentificacion() throws Exception {
        when(afiliadoService.findByNumeroIdentificacion("1000185557")).thenReturn(getAnyAfiliadoDto());

        mockMvc.perform(get("/api/v1/afiliado/filtrarIdentificacion")
                .content(MediaType.APPLICATION_JSON_VALUE)
                .param("numeroIdentificacion", "1000185557"))
                .andExpect(jsonPath("$.numeroIdentificacion", is("1000185557")))
                .andExpect(jsonPath("$.fechaCreacion", is("2020-11-17 16:00:00.000")))
                .andExpect(jsonPath("$.afiliadoId", is(1)))
                .andExpect(status().isOk());
    }

    @Test
    public void shoulgGetAfiliadosByUsuarioCreacion() throws Exception{
        when(afiliadoService.findByUsuarioCreacion("DESPINOG")).thenReturn(getAnyListAfiliadosDto());

        mockMvc.perform(get("/api/v1/afiliado/filtrarUsuarioCreacion").
                content(MediaType.APPLICATION_JSON_VALUE)
                .param("usuarioCreacion", "DESPINOG"))
                .andExpect(jsonPath("$[0].usuarioCreacion", is("DESPINOG")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetAfiliadosByFechaCreacion() throws Exception{
        when(afiliadoService.filterByFechaCreacion("2020-11-17 16:00:00.000", "2020-12-17 16:00:00.000"))
                .thenReturn(getAnyListAfiliadosDto());

        mockMvc.perform(get("/api/v1/afiliado/filtrarFechaCreacion")
                .content(MediaType.APPLICATION_JSON_VALUE)
                .param("fromDate", "2020-11-17 16:00:00.000")
                .param("toDate","2020-12-17 16:00:00.000"))
                .andExpect(jsonPath("$[0].fechaCreacion", is("2020-11-17 16:00:00.000")))
                .andExpect(jsonPath("$[1].fechaCreacion", is("2020-11-26 16:00:00.000")))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSaveAfiliado() throws Exception {
        mockMvc.perform(post("/api/v1/afiliado")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getAnyAfiliadoDto())))
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateAfiliado() throws Exception{
        mockMvc.perform(put("/api/v1/afiliado")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(getAnyAfiliadoDto())))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteAfiliado() throws Exception{
        mockMvc.perform(delete("/api/v1/afiliado/1")
                .content(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());

    }
}
