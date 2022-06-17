package com.nttdata.mscasos.domain.repository;

import com.nttdata.mscasos.domain.entity.Caso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CasoRepository extends JpaRepository<Caso, Integer> {

    List<Caso> findCasosByUsuarioCreacion(String usuarioCreacion);

    List<Caso> findCasosByGestorId(String gestorId);

    @Query("select c from Caso c where c.fechaInicioCaso >= :fromDate and c.fechaInicioCaso <= :toDate")
    List<Caso> filterCasosByFechaInicioCaso(String fromDate, String toDate);
}
