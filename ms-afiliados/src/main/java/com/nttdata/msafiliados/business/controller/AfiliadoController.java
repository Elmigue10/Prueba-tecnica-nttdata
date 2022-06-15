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

    @PostMapping("")
    public ResponseEntity saveAfiliado(@RequestBody Afiliado afiliado){
        afiliadoService.saveAfiliado(afiliado);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity updatedAfiliado(@RequestBody Afiliado afiliado){
        afiliadoService.updateAfiliado(afiliado);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteAfiliadoById(@PathVariable Integer id){
        afiliadoService.deleteAfiliadoById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
