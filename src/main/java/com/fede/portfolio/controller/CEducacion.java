/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.controller;



import com.fede.portfolio.dto.EducacionDto;
import com.fede.portfolio.dto.response.MessageResponse;
import com.fede.portfolio.mapper.EducacionMapper;
import com.fede.portfolio.model.Educacion;
import com.fede.portfolio.service.SEducacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/educacion")
public class CEducacion {
    
    @Autowired
    SEducacion sEducacion;

    @Autowired
    EducacionMapper educacionMapper;
    
    @GetMapping("/all")
    public ResponseEntity<List<EducacionDto>> getAll(){
        return new ResponseEntity<>(
                sEducacion.getAll()
                        .stream()
                        .map(educacion -> educacionMapper.mapToDto(educacion))
                        .toList(),
                HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable("id")Long id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity<>(new MessageResponse("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = sEducacion.getOne(id);
        return new ResponseEntity<>(educacionMapper.mapToDto(educacion), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity<>(new MessageResponse("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        sEducacion.delete(id);
        return new ResponseEntity<>(new MessageResponse("Educacion eliminada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER','ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody EducacionDto dtoeduc){
        if((dtoeduc.getNombreE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if((dtoeduc.getDesdehastaE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        }

        Educacion educacion = new Educacion(dtoeduc.getNombreE(), dtoeduc.getDescripcionE(),dtoeduc.getDesdehastaE());
        sEducacion.save(educacion);
        return new ResponseEntity<>(new MessageResponse("Educacion creada"), HttpStatus.CREATED);
          
    }
    
    @PreAuthorize("hasRole('USER','ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable("id") Long id,
            @RequestBody EducacionDto dtoeduc
    ){
        if(!sEducacion.existsById(id)){
            return new ResponseEntity<>(new MessageResponse("No existe el ID"), HttpStatus.NOT_FOUND);
        }

        if((dtoeduc.getNombreE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if((dtoeduc.getDesdehastaE()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("La fecha es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = sEducacion.getOne(id);

        educacion.setNombreE(dtoeduc.getNombreE());
        educacion.setDescripcionE(dtoeduc.getDescripcionE());
        educacion.setDesdehastaE(dtoeduc.getDesdehastaE());
        
        sEducacion.save(educacion);
        
        return new ResponseEntity<>(new MessageResponse("Educacion actualizada"), HttpStatus.OK);
    }
}
