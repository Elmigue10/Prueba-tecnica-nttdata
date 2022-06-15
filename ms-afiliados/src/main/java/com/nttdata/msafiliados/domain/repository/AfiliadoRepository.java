package com.nttdata.msafiliados.domain.repository;

import com.nttdata.msafiliados.domain.entity.Afiliado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AfiliadoRepository extends JpaRepository<Afiliado, Integer> {

    Afiliado findAfiliadoByNumeroIdentificacion(String numeroIdentificacion);

    @Query("SELECT a FROM Afiliado a WHERE a.usuarioCreacion=:usuarioCreacion")
    List<Afiliado> findAfiliadoByUsuarioCreacion(@Param("usuarioCreacion") String usuarioCreacion);

    @Query("select a from Afiliado a where a.fechaCreacion >= :fromDate and a.fechaCreacion < :toDate")
    List<Afiliado> filterByFechaCreacion(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

}
