
package com.fede.portfolio.controller;


import com.fede.portfolio.dto.HySDto;
import com.fede.portfolio.dto.response.MessageResponse;
import com.fede.portfolio.mapper.HySMapper;
import com.fede.portfolio.model.HyS;
import com.fede.portfolio.service.Shys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/skill")
public class CHys {

    @Autowired
    Shys shys;

    @Autowired
    HySMapper hySMapper;
    
    @GetMapping("/all")
    public ResponseEntity<List<HySDto>> getAll() {
        List<HySDto> list = shys.getAll()
                .stream()
                .map(HyS -> hySMapper.mapToDto(HyS))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        }
        HyS hYs = shys.getOne(id);
        return new ResponseEntity<>(hYs, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        }
        shys.delete(id);
        return new ResponseEntity<>(new MessageResponse("Skill eliminada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody HySDto dtohys) {
        if ((dtohys.getNombre()).isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if ((dtohys.getSubtitle()).isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("El subtitulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HyS hYs = new HyS(dtohys.getNombre(), dtohys.getPorcentaje(), dtohys.getSubtitle());
        shys.save(hYs);

        return new ResponseEntity<>(new MessageResponse("Skill agregada"), HttpStatus.CREATED);
    }
    
    @PreAuthorize("hasRole('USER', 'ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(
            @RequestBody HySDto dtohys
    ) {
        Long id = dtohys.getId();
        //Validamos si existe el ID
        if (!shys.existsById(id)) {
            return new ResponseEntity<>(new MessageResponse("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if ((dtohys.getSubtitle()).isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("El subtitulo es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HyS hYs = shys.getOne(id);
        hYs.setNombre(dtohys.getNombre());
        hYs.setPorcentaje(dtohys.getPorcentaje());
        hYs.setSubtitle(dtohys.getSubtitle());

        shys.save(hYs);
        return new ResponseEntity<>(new MessageResponse("Skill actualizada"), HttpStatus.OK);
    }
}