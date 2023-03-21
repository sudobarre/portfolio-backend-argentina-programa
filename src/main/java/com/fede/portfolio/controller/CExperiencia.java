/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.controller;


import com.fede.portfolio.dto.ExperienciaDto;
import com.fede.portfolio.dto.response.MessageResponse;
import com.fede.portfolio.model.Experiencia;
import com.fede.portfolio.service.SExperiencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/experiencia")
public class CExperiencia {
    
    @Autowired
    SExperiencia sExperiencia;
    
    @GetMapping("/all")
    public ResponseEntity<List<ExperienciaDto>> list(){
        List<ExperienciaDto> list = sExperiencia.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if(!sExperiencia.existsById(id)) return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(sExperiencia.getOne(id), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!sExperiencia.existsById(id)) {
            return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        }
        sExperiencia.delete(id);
        return new ResponseEntity<>(new MessageResponse("Experiencia eliminada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ExperienciaDto dtoexp){
        if((dtoexp.getNombreE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        //if(sExperiencia.existsByNombreE(dtoexp.getNombreE())){
        //    return new ResponseEntity(new MessageResponse("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        //}
        if((dtoexp.getDesdehastaE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(),dtoexp.getDescripcionE(), dtoexp.getDesdehastaE());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity<>(new MessageResponse("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER','ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @RequestBody ExperienciaDto dtoexp
    ){
        //Validamos si existe el ID
        if(!sExperiencia.existsById(id)){
            return new ResponseEntity<>(new MessageResponse("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        if((dtoexp.getNombreE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if((dtoexp.getDesdehastaE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Experiencia experiencia = sExperiencia.getOne(id);
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE(dtoexp.getDescripcionE());
        experiencia.setDesdehastaE(dtoexp.getDesdehastaE());

        sExperiencia.save(experiencia);
        return new ResponseEntity<>(new MessageResponse("Experiencia actualizada"), HttpStatus.OK);
    } 
}