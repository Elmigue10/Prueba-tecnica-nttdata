package com.nttdata.msafiliados.business.controller;

import com.nttdata.msafiliados.business.service.AfiliadoService;
import com.nttdata.msafiliados.domain.entity.Afiliado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/afiliado")
public class AfiliadoController {

    private final AfiliadoService afiliadoService;

    public AfiliadoController(AfiliadoService afiliadoService){
        this.afiliadoService = afiliadoService;
    }

    @GetMapping("")
    public ResponseEntity<List<Afiliado>> getAll(){
        return new ResponseEntity<>(afiliadoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afiliado> findById(@PathVariable Integer id){
        return new ResponseEntity<>(afiliadoService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/filtrarIdentificacion")
    public ResponseEntity<Afiliado> findByNumeroIdentificacion(@RequestParam String numeroIdentificacion){
        return new ResponseEntity<>(afiliadoService.findByNumeroIdentificacion(numeroIdentificacion), HttpStatus.OK);
    }

    @GetMapping("/filtrarUsuarioCreacion")
    public ResponseEntity<List<Afiliado>> filtrar(@RequestParam String usuarioCreacion){
        return new ResponseEntity<>(afiliadoService.findByUsuarioCreacion(usuarioCreacion), HttpStatus.OK);
    }

    @GetMapping("/filtrarFechaCreacion")
    public ResponseEntity<List<Afiliado>> filtrarFechaCreacion(@RequestParam String fromDate,
                                                               @RequestParam String toDate){
        return new ResponseEntity<>(afiliadoService.filterByFechaCreacion(fromDate, toDate), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> saveAfiliado(@RequestBody Afiliado afiliado){
        afiliadoService.saveAfiliado(afiliado);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<Object> updatedAfiliado(@RequestBody Afiliado afiliado){
        afiliadoService.updateAfiliado(afiliado);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAfiliadoById(@PathVariable Integer id){
        afiliadoService.deleteAfiliadoById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
