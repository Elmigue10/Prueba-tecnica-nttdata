package com.nttdata.msafiliados;

import com.nttdata.msafiliados.business.service.AfiliadoService;
import com.nttdata.msafiliados.domain.dto.AfiliadoDto;
import com.nttdata.msafiliados.domain.entity.Afiliado;
import com.nttdata.msafiliados.domain.exception.ApiRequestException;
import com.nttdata.msafiliados.domain.repository.AfiliadoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AfiliadoServiceTest extends AbstractTest{

    @Autowired
    private AfiliadoService afiliadoService;

    @MockBean
    private AfiliadoRepository afiliadoRepository;

    private List<AfiliadoDto> afiliadosDto = new ArrayList<>();
    private AfiliadoDto afiliadoDto = new AfiliadoDto();
    private Afiliado afiliado = new Afiliado();

    @Test
    public void shouldListAfiliados(){
        when(afiliadoRepository.findAll()).thenReturn(getAnyListAfiliados());
        afiliadosDto = afiliadoService.getAll();

        assertTrue(afiliadosDto.size() >= 0);

        verify(afiliadoRepository, times(1)).findAll();
    }

    @Test
    public void shouldGetAfiliadoById(){
        when(afiliadoRepository.findById(1)).thenReturn(java.util.Optional.of(getAnyAfiliado()));
        afiliadoDto = afiliadoService.findById(1);

        assertEquals("1000185557", afiliadoDto.getNumeroIdentificacion());

        verify(afiliadoRepository, times(1)).findById(1);
    }

    @Test
    public void shouldGetAfiliadoByIdentificacion() {
        when(afiliadoRepository.findAfiliadoByNumeroIdentificacion("1000185557"))
                .thenReturn(getAnyAfiliado());
        afiliadoDto = afiliadoService.findByNumeroIdentificacion("1000185557");
        assertEquals("1000185557", afiliadoDto.getNumeroIdentificacion());
        assertEquals("CC", afiliadoDto.getTipoIdentificacion());

        verify(afiliadoRepository, times(1)).findAfiliadoByNumeroIdentificacion("1000185557");
    }

    @Test
    public void shouldGetExceptionWhenIdentificacionIsEmpty(){
        ApiRequestException apiRequestException = Assertions.catchThrowableOfType(
                () -> afiliadoService.findByNumeroIdentificacion(""), ApiRequestException.class);

        assertEquals("El HttpQuery 'numeroIdentificacion' es requerido'", apiRequestException.getMessage());
    }

    @Test
    public void shouldGetAfiliadoByUsuarioCreacion(){
        when(afiliadoRepository.findAfiliadoByUsuarioCreacion("DESPINOG"))
                .thenReturn(getAnyListAfiliados());
        afiliadosDto = afiliadoService.findByUsuarioCreacion("DESPINOG");

        assertTrue(afiliadosDto.size() >= 0);
        assertEquals("DESPINOG", afiliadosDto.get(0).getUsuarioCreacion());

        verify(afiliadoRepository, times(1)).findAfiliadoByUsuarioCreacion("DESPINOG");
    }

    @Test
    public void shouldGetExceptionWhenUsuarioCreacionIsEmpty(){
        ApiRequestException apiRequestException = Assertions.catchThrowableOfType(
                () -> afiliadoService.findByUsuarioCreacion(""), ApiRequestException.class);

        assertEquals("El HttpQuery 'usuarioCreación' es requerido'", apiRequestException.getMessage());
    }

    @Test
    public void shouldGetAfiliadosByFechaCreacion(){
        when(afiliadoRepository.filterByFechaCreacion("2020-11-17 16:00:00.000", "2020-11-26 16:00:00.000"))
                .thenReturn(getAnyListAfiliados());

        afiliadosDto = afiliadoService.filterByFechaCreacion("2020-11-17 16:00:00.000", "2020-11-26 16:00:00.000");
        assertTrue(afiliadosDto.size() > 0);
        assertEquals("2020-11-26 16:00:00.000", afiliadosDto.get(1).getFechaCreacion());

        verify(afiliadoRepository, times(1))
                .filterByFechaCreacion("2020-11-17 16:00:00.000", "2020-11-26 16:00:00.000");
    }

    @Test
    public void shouldGetExceptionWhenFechaCreacionIsEmpty(){
        ApiRequestException apiRequestException = Assertions.catchThrowableOfType(
                () -> afiliadoService.filterByFechaCreacion("",""), ApiRequestException.class);

        assertEquals("Los HttpQuery 'fromDate' y 'toDate' son requeridos'", apiRequestException.getMessage());
    }

    @Test
    public void shouldSaveAfiliado(){
        afiliadoDto = getAnyAfiliadoDto();
        when(afiliadoRepository.save(Mockito.any(Afiliado.class))).thenReturn(afiliado);
        afiliadoService.saveAfiliado(afiliadoDto);
        verify(afiliadoRepository, times(1)).save(Mockito.any(Afiliado.class));
    }

    @Test
    public void shouldGetExceptionWhenTipoIdentificacionIsInvalid(){
        afiliadoDto = getAnyAfiliadoDto();
        afiliadoDto.setTipoIdentificacion("cedula");
        ApiRequestException apiRequestException = Assertions.catchThrowableOfType(
                () -> afiliadoService.saveAfiliado(afiliadoDto), ApiRequestException.class);

        assertEquals("Tipo de documento invalido.", apiRequestException.getMessage());
    }

    @Test
    public void shouldUpdateAfiliado(){
        afiliadoDto = getAnyAfiliadoDto();
        when(afiliadoRepository.findById(1)).thenReturn(java.util.Optional.of(getAnyAfiliado()));
        when(afiliadoRepository.save(Mockito.any(Afiliado.class))).thenReturn(afiliado);
        afiliadoService.updateAfiliado(afiliadoDto);
        verify(afiliadoRepository, times(1)).findById(1);
        verify(afiliadoRepository, times(1)).save(Mockito.any(Afiliado.class));
    }

    @Test
    public void shouldDeleteAfiliado(){
        afiliadoService.deleteAfiliadoById(1);
        verify(afiliadoRepository, times(1)).deleteById(1);
    }
}
