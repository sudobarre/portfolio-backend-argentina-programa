
package com.fede.portfolio.controller;

import com.fede.portfolio.dto.PersonaDto;
import com.fede.portfolio.dto.response.MessageResponse;
import com.fede.portfolio.mapper.PersonaMapper;
import com.fede.portfolio.model.Persona;
import com.fede.portfolio.service.SPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/personas")
public class CPersona {
    @Autowired
    SPersona sPersona;

    @Autowired
    PersonaMapper personaMapper;
    
    @GetMapping("/all")
    public ResponseEntity<List<PersonaDto>> getAll(){
        List<Persona> list = sPersona.getAll();
        return new ResponseEntity<>(
                list.stream()
                        .map(personaMapper::toDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
        public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if(!sPersona.existsById(id)) {
            return new ResponseEntity<>(new MessageResponse("Persona no existe"), HttpStatus.NOT_FOUND);
        }
        Persona persona = sPersona.getOne(id);
        PersonaDto personaDto = personaMapper.toDto(persona);
        return new ResponseEntity<>(personaDto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!sPersona.existsById(id)) {
            return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        }
        sPersona.delete(id);
        return new ResponseEntity<>(new MessageResponse("Persona eliminada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER', ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody PersonaDto personaDto){
        if((personaDto.getNombre()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        sPersona.save(personaMapper.toPersona(personaDto));
        return new ResponseEntity<>(new MessageResponse("Persona agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER', 'ADMIN')")
    @PutMapping("/{id}")
    //Los campos no pueden estar vacios de todas maneras por los @NotNull del dto
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody PersonaDto personaDto){
        //Validamos si existe el ID
        if(!sPersona.existsById(id)){
            return new ResponseEntity<>(new MessageResponse("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if((personaDto.getNombre()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if((personaDto.getApellido()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El apellido es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if((personaDto.getImg()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("La imagen es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if((personaDto.getDescripcion()).isEmpty()) {
            return new ResponseEntity<>(new MessageResponse("La descripcion es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        
        Persona persona = sPersona.getOne(id);
        
        persona.setNombre(personaDto.getNombre());
        persona.setDescripcion(personaDto.getDescripcion());
        persona.setImg(personaDto.getImg());
        
        
        sPersona.save(persona);
        return new ResponseEntity<>(new MessageResponse("Persona actualizada"), HttpStatus.OK);
    } 
}