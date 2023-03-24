/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.controller;


import com.fede.portfolio.dto.ExperienciaDto;
import com.fede.portfolio.dto.response.MessageResponse;
import com.fede.portfolio.mapper.ExperienciaMapper;
import com.fede.portfolio.model.Experiencia;
import com.fede.portfolio.service.SExperiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/experience")
public class CExperiencia {
    
    @Autowired
    SExperiencia sExperiencia;

    @Autowired
    ExperienciaMapper experienciaMapper;
    
    @GetMapping("/all")
    public ResponseEntity<List<ExperienciaDto>> getAll(){
        List<ExperienciaDto> list = sExperiencia.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if(!sExperiencia.existsById(id)) return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(experienciaMapper.mapToDto(sExperiencia.getOne(id)), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!sExperiencia.existsById(id)) {
            return new ResponseEntity<>(new MessageResponse("no existe id:" + id), HttpStatus.NOT_FOUND);
        }
        sExperiencia.delete(id);
        return new ResponseEntity<>(new MessageResponse("Experiencia eliminada"), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExperienciaDto dtoexp){
        if((dtoexp.getNombreE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if((dtoexp.getDesdeE()).isEmpty() || dtoexp.getHastaE().isEmpty()){
            return new ResponseEntity<>(new MessageResponse("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        sExperiencia.save(dtoexp);
        
        return new ResponseEntity<>(new MessageResponse("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(
            @RequestBody ExperienciaDto dtoexp
    ){
        Long id = dtoexp.getId();
        //Validamos si existe el ID
        if(!sExperiencia.existsById(id)){
            return new ResponseEntity<>(new MessageResponse("El ID no existe: " + id), HttpStatus.BAD_REQUEST);
        }
        if((dtoexp.getNombreE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if((dtoexp.getDesdeE()).isEmpty() || dtoexp.getHastaE().isEmpty()){
            return new ResponseEntity<>(new MessageResponse("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = sExperiencia.getOne(id);
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE(dtoexp.getDescripcionE());
        experiencia.setDesdeE(dtoexp.getDesdeE());
        experiencia.setHastaE(dtoexp.getHastaE());
        experiencia.setImgUrl(dtoexp.getImgUrl());

        sExperiencia.update(experiencia);
        return new ResponseEntity<>(new MessageResponse("Experiencia actualizada"), HttpStatus.OK);
    } 
}