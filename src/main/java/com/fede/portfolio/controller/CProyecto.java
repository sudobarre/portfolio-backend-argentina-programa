
package com.fede.portfolio.controller;

import com.fede.portfolio.dto.ProyectoDto;
import com.fede.portfolio.dto.response.MessageResponse;
import com.fede.portfolio.mapper.ProyectoMapper;
import com.fede.portfolio.model.Proyecto;
import com.fede.portfolio.service.SProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/project")
public class CProyecto {
    
    @Autowired
    SProyecto sProyecto;

    @Autowired
    ProyectoMapper proyectoMapper;

    @GetMapping("/all")
    public ResponseEntity<List<ProyectoDto>> list(){
        List<Proyecto> list = sProyecto.getAll();
        return new ResponseEntity<>(
                list.stream()
                        .map(proyecto -> proyectoMapper.toDto(proyecto))
                        .collect(Collectors.toList())
                , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        if(!sProyecto.existsById(id))
            return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        Proyecto proyecto = sProyecto.getOne(id);
        return new ResponseEntity<>(proyectoMapper.toDto(proyecto), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER','ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (!sProyecto.existsById(id)) {
            return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        }
        sProyecto.delete(id);
        return new ResponseEntity<>(new MessageResponse("Proyecto eliminado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER', 'ADMIN')")
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ProyectoDto dtoproy){
        if((dtoproy.getNombreP()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El título es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        if((dtoproy.getLinkP()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El link es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Proyecto proyecto = new Proyecto (dtoproy.getNombreP(), dtoproy.getDescripcionP(), dtoproy.getLinkP());        
        sProyecto.save(proyecto);
        
        return new ResponseEntity<>(new MessageResponse("Proyecto agregado"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('USER','ADMIN')")
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProyectoDto dtoproy){
        //Validamos si existe el ID
        Long id = dtoproy.getId();
        if(!sProyecto.existsById(id)){
            return new ResponseEntity<>(new MessageResponse("El ID no existe"), HttpStatus.BAD_REQUEST);
        }

        if((dtoproy.getNombreP()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if((dtoproy.getLinkP()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El link es obligatorio"), HttpStatus.BAD_REQUEST);
        }


        Proyecto proyecto = sProyecto.getOne(id);
        proyecto.setNombreP(dtoproy.getNombreP());
        proyecto.setDescripcionP(dtoproy.getDescripcionP());
        proyecto.setLinkP(dtoproy.getLinkP());

        sProyecto.save(proyecto);
        return new ResponseEntity<>(new MessageResponse("Proyecto actualizado"), HttpStatus.OK);
    } 
}