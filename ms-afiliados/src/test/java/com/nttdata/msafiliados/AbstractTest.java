package com.nttdata.msafiliados;

import com.nttdata.msafiliados.domain.dto.AfiliadoDto;
import com.nttdata.msafiliados.domain.entity.Afiliado;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractTest {

    private List<AfiliadoDto> afiliadoDtos = new ArrayList<>();
    private List<Afiliado> afiliados = new ArrayList<>();

    public List<AfiliadoDto> getAnyListAfiliadosDto(){

        afiliadoDtos.add(new AfiliadoDto(1,"CC","1000185557",
                "Miguel", "Angel", "Valbuena", "Olaya",
                "RM", 'S', "DESPINOG", "2020-11-17 16:00:00.000",
                null, null, "4124123", "VIGENTE"));

        afiliadoDtos.add(new AfiliadoDto(1,"CC","4234123",
                "Thomas", "Eduardo", "Cortes", "Gomez",
                "RM", 'S', "DESPINOG", "2020-11-26 16:00:00.000",
                null, null, "4124123", "VIGENTE"));

        return afiliadoDtos;
    }

    public AfiliadoDto getAnyAfiliadoDto(){
        return new AfiliadoDto(1,"CC","1000185557",
                "Miguel", "Angel", "Valbuena", "Olaya",
                "RM", 'S', "DESPINOG", "2020-11-17 16:00:00.000",
                null, null, "4124123", "VIGENTE");
    }

    public List<Afiliado> getAnyListAfiliados(){

        afiliados.add(new Afiliado(1,"CC","1000185557",
                "Miguel", "Angel", "Valbuena", "Olaya",
                "RM", 'S', "DESPINOG", "2020-11-17 16:00:00.000",
                null, null, "4124123", "VIGENTE"));

        afiliados.add(new Afiliado(1,"CC","4234123",
                "Thomas", "Eduardo", "Cortes", "Gomez",
                "RM", 'S', "DESPINOG", "2020-11-26 16:00:00.000",
                null, null, "4124123", "VIGENTE"));

        return afiliados;
    }

    public Afiliado getAnyAfiliado(){
        return new Afiliado(1,"CC","1000185557",
                "Miguel", "Angel", "Valbuena", "Olaya",
                "RM", 'S', "DESPINOG", "2020-11-17 16:00:00.000",
                null, null, "4124123", "VIGENTE");
    }

}
