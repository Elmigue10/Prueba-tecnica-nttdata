package com.nttdata.mscasos.business.service;

import com.nttdata.mscasos.domain.dto.CasoDto;
import com.nttdata.mscasos.domain.entity.Caso;
import com.nttdata.mscasos.domain.exception.ApiRequestException;
import com.nttdata.mscasos.domain.repository.CasoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CasoService {

    private final CasoRepository casoRepository;
    private ModelMapper modelMapper = new ModelMapper();

    public CasoService(CasoRepository casoRepository){
        this.casoRepository = casoRepository;
    }

    public List<CasoDto> getAll(){
        List<Caso> casos = casoRepository.findAll();
        return mapListCasosToDto(casos);
    }

    public CasoDto findById(Integer id){
        Caso caso = casoRepository.findById(id).get();
        return modelMapper.map(caso, CasoDto.class);
    }

    public List<CasoDto> findCasosByUsuarioCreacion(String usuarioCreacion){
        if(usuarioCreacion.isEmpty()){
            throw new ApiRequestException("El HttpQuery 'usuarioCreación' es requerido'");
        }
        List<Caso> casos = casoRepository.findCasosByUsuarioCreacion(usuarioCreacion);
        return mapListCasosToDto(casos);
    }

    public List<CasoDto> findCasosByGestorId(String gestorId){
        if(gestorId.isEmpty()){
            throw new ApiRequestException("El HttpQuery 'gestorId' es requerido'");
        }
        List<Caso> casos = casoRepository.findCasosByGestorId(gestorId);
        return mapListCasosToDto(casos);
    }

    public List<CasoDto> filterCasosByFechaInicioCaso(String fromDate, String toDate){
        if(fromDate.isEmpty() || toDate.isEmpty()){
            throw new ApiRequestException("Los HttpQuery 'fromDate' y 'toDate' son requeridos'");
        }
        List<Caso> casos = casoRepository.filterCasosByFechaInicioCaso(fromDate, toDate);
        return mapListCasosToDto(casos);
    }

    public void saveCaso(CasoDto casoDto){
        casoRepository.save(modelMapper.map(casoDto, Caso.class));
    }

    public void updateCaso(CasoDto casoDto){
        Caso caso = casoRepository.findById(casoDto.getIdCaso()).get();

        caso.setItemType(casoDto.getItemType());
        caso.setItemKey(casoDto.getItemKey());
        caso.setProceso(casoDto.getProceso());
        caso.setProcesoEtapa(casoDto.getProcesoEtapa());
        caso.setFechaActualEtapa(casoDto.getFechaActualEtapa());
        caso.setClasificacionDeuda(casoDto.getClasificacionDeuda());
        caso.setOrigenAsignacionCat(casoDto.getOrigenAsignacionCat());
        caso.setComportamiento(casoDto.getComportamiento());
        caso.setTipoIdentificacionEmp(casoDto.getTipoIdentificacionEmp());
        caso.setNumeroIdentificacionEmp(casoDto.getNumeroIdentificacionEmp());
        caso.setRiesgoEmpCat(casoDto.getRiesgoEmpCat());
        caso.setPeriodoCosecha(casoDto.getPeriodoCosecha());
        caso.setFechaUltimoPago(casoDto.getFechaUltimoPago());
        caso.setUltimoPeriodoPagado(casoDto.getUltimoPeriodoPagado());
        caso.setEstadoCasoCat(casoDto.getEstadoCasoCat());
        caso.setGestorId(casoDto.getGestorId());
        caso.setPeriodoInicial(casoDto.getPeriodoInicial());
        caso.setPeriodoFinal(casoDto.getPeriodoFinal());
        caso.setFechaInicioCaso(casoDto.getFechaInicioCaso());
        caso.setFechaInicioProceso(casoDto.getFechaInicioProceso());
        caso.setProcesoCausal(casoDto.getProcesoCausal());
        caso.setFechaProcesoCausal(casoDto.getFechaProcesoCausal());
        caso.setExcluirComunicado(casoDto.getExcluirComunicado());
        caso.setSolicitudCobroId(casoDto.getSolicitudCobroId());
        caso.setUsuarioUltimaModificacion(casoDto.getUsuarioUltimaModificacion());
        caso.setFechaUltimaModificacion(casoDto.getFechaUltimaModificacion());
        caso.setGestorExternoId(casoDto.getGestorExternoId());
        caso.setFechaCorte(casoDto.getFechaCorte());
        caso.setGestionExtendida(casoDto.getGestionExtendida());
        caso.setGestionExtendidaFecha(casoDto.getGestionExtendidaFecha());

        casoRepository.save(caso);
    }

    public void deleteCasoById(Integer id){
        casoRepository.deleteById(id);
    }

    public List<CasoDto> mapListCasosToDto(List<Caso> casos){
        List<CasoDto> casosDto = new ArrayList<>();
        CasoDto casoDto;
        for (Caso c : casos) {
            casoDto = modelMapper.map(c, CasoDto.class);
            casosDto.add(casoDto);
        }
        return casosDto;
    }

}
