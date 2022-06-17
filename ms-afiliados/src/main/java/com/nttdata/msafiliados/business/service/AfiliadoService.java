package com.nttdata.msafiliados.business.service;

import com.nttdata.msafiliados.domain.dto.AfiliadoDto;
import com.nttdata.msafiliados.domain.util.TipoDocumento;
import com.nttdata.msafiliados.domain.entity.Afiliado;
import com.nttdata.msafiliados.domain.exception.ApiRequestException;
import com.nttdata.msafiliados.domain.repository.AfiliadoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AfiliadoService {

    private final AfiliadoRepository afiliadoRepository;
    private ModelMapper modelMapper = new ModelMapper();
    private TipoDocumento[] tiposDocumento = TipoDocumento.values();

    public AfiliadoService(AfiliadoRepository afiliadoRepository){
        this.afiliadoRepository = afiliadoRepository;
    }

    public List<AfiliadoDto> getAll(){
        List<Afiliado> afiliados = afiliadoRepository.findAll();
        return mapListAfiliadosToDto(afiliados);
    }

    public AfiliadoDto findById(Integer id){
        Afiliado afiliado = afiliadoRepository.findById(id).orElse(null);
        return modelMapper.map(afiliado, AfiliadoDto.class);
    }

    public AfiliadoDto findByNumeroIdentificacion(String numeroIdentificacion){
        if(numeroIdentificacion.isEmpty()){
            throw new ApiRequestException("El HttpQuery 'numeroIdentificacion' es requerido'");
        }
        Afiliado afiliado = afiliadoRepository.findAfiliadoByNumeroIdentificacion(numeroIdentificacion);
        return modelMapper.map(afiliado, AfiliadoDto.class);
    }

    public List<AfiliadoDto> findByUsuarioCreacion(String usuarioCreacion){
        if(usuarioCreacion.isEmpty()){
            throw new ApiRequestException("El HttpQuery 'usuarioCreación' es requerido'");
        }
        List<Afiliado> afiliados = afiliadoRepository.findAfiliadoByUsuarioCreacion(usuarioCreacion);
        return mapListAfiliadosToDto(afiliados);
    }

    public List<AfiliadoDto> filterByFechaCreacion(String fromDate, String toDate){
        if(fromDate.isEmpty() || toDate.isEmpty()){
            throw new ApiRequestException("Los HttpQuery 'fromDate' y 'toDate' son requeridos'");
        }
        List<Afiliado> afiliados = afiliadoRepository.filterByFechaCreacion(fromDate, toDate);
        return mapListAfiliadosToDto(afiliados);
    }

    public void saveAfiliado(AfiliadoDto afiliadoDto){
        if (Arrays.stream(tiposDocumento).map(TipoDocumento::getName)
                .noneMatch(afiliadoDto.getTipoIdentificacion()::equals)) {
            throw new ApiRequestException("Tipo de documento invalido.");
        }
        afiliadoRepository.save(modelMapper.map(afiliadoDto, Afiliado.class));
    }

    public void updateAfiliado(AfiliadoDto afiliadoDtoReq){
        AfiliadoDto afiliadoDto = findById(afiliadoDtoReq.getAfiliadoId());

        Afiliado afiliado = modelMapper.map(afiliadoDto, Afiliado.class);

        afiliado.setTipoIdentificacion(afiliadoDtoReq.getTipoIdentificacion());
        afiliado.setNumeroIdentificacion(afiliadoDtoReq.getNumeroIdentificacion());
        afiliado.setPrimerNombre(afiliadoDtoReq.getPrimerNombre());
        afiliado.setSegundoNombre(afiliadoDtoReq.getSegundoNombre());
        afiliado.setPrimerApellido(afiliadoDtoReq.getPrimerApellido());
        afiliado.setSegundoApellido(afiliadoDtoReq.getSegundoApellido());
        afiliado.setRiesgoCat(afiliadoDtoReq.getRiesgoCat());
        afiliado.setActivo(afiliadoDtoReq.getActivo());
        afiliado.setUsuarioUltimaModificacion(afiliadoDtoReq.getUsuarioUltimaModificacion());
        afiliado.setFechaUltimaModificacion(afiliadoDtoReq.getFechaUltimaModificacion());
        afiliado.setNumeroCuenta(afiliadoDtoReq.getNumeroCuenta());
        afiliado.setEstadoCuenta(afiliadoDtoReq.getEstadoCuenta());

        afiliadoRepository.save(afiliado);
    }

    public void deleteAfiliadoById(Integer id){
        afiliadoRepository.deleteById(id);
    }

    public List<AfiliadoDto> mapListAfiliadosToDto(List<Afiliado> afiliados){
        List<AfiliadoDto> afiliadosDto = new ArrayList<>();
        AfiliadoDto afiliadoDto;
        for (Afiliado a : afiliados) {
            afiliadoDto = modelMapper.map(a, AfiliadoDto.class);
            afiliadosDto.add(afiliadoDto);
        }
        return afiliadosDto;
    }
}
