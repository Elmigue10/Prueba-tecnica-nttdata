package com.nttdata.msafiliados.business.service;

import com.nttdata.msafiliados.domain.util.TipoDocumento;
import com.nttdata.msafiliados.domain.entity.Afiliado;
import com.nttdata.msafiliados.domain.exception.ApiRequestException;
import com.nttdata.msafiliados.domain.repository.AfiliadoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class AfiliadoService {

    private final AfiliadoRepository afiliadoRepository;
    private Afiliado afiliado;
    private TipoDocumento[] tiposDocumento;

    public AfiliadoService(AfiliadoRepository afiliadoRepository){
        this.afiliadoRepository = afiliadoRepository;
        this.afiliado = new Afiliado();
        this.tiposDocumento = TipoDocumento.values();
    }

    public List<Afiliado> getAll(){ return afiliadoRepository.findAll(); }

    public Afiliado findById(Integer id){ return afiliadoRepository.findById(id).get(); }

    public void saveAfiliado(Afiliado afiliadoReq){

        if (!Arrays.stream(tiposDocumento).map(TipoDocumento::getName)
                .filter(afiliadoReq.getTipoIdentificacion()::equals).findFirst().isPresent()) {
            throw new ApiRequestException("Tipo de documento invalido.");
        }
        afiliadoRepository.save(afiliado);

    }

    public void updateAfiliado(Afiliado afiliadoReq){
        afiliado = findById(afiliadoReq.getAfiliadoId());
        afiliado.setTipoIdentificacion(afiliadoReq.getTipoIdentificacion());
        afiliado.setNumeroIdentificacion(afiliadoReq.getNumeroIdentificacion());
        afiliado.setPrimerNombre(afiliadoReq.getPrimerNombre());
        afiliado.setSegundoNombre(afiliadoReq.getSegundoNombre());
        afiliado.setPrimerApellido(afiliadoReq.getPrimerApellido());
        afiliado.setSegundoApellido(afiliadoReq.getSegundoApellido());
        afiliado.setRiesgoCat(afiliadoReq.getRiesgoCat());
        afiliado.setActivo(afiliadoReq.getActivo());
        afiliado.setUsuarioUltimaModificacion(afiliadoReq.getUsuarioUltimaModificacion());
        afiliado.setFechaUltimaModificacion(afiliadoReq.getFechaUltimaModificacion());
        afiliado.setNumeroCuenta(afiliadoReq.getNumeroCuenta());
        afiliado.setEstadoCuenta(afiliadoReq.getEstadoCuenta());

        afiliadoRepository.save(afiliado);
    }

    public void deleteAfiliadoById(Integer id){
        afiliadoRepository.deleteById(id);
    }

}
