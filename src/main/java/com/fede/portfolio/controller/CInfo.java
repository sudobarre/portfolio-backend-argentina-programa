
package com.fede.portfolio.controller;

import com.fede.portfolio.dto.InfoDto;
import com.fede.portfolio.dto.response.MessageResponse;
import com.fede.portfolio.mapper.InfoMapper;
import com.fede.portfolio.model.Info;
import com.fede.portfolio.repository.UserRepository;
import com.fede.portfolio.service.SInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/info")
public class CInfo {
    @Autowired
    SInfo sInfo;

    @Autowired
    InfoMapper infoMapper;


    @GetMapping("/{name}")
        public ResponseEntity<?> getByUsername(@PathVariable("name") String name){
        if(!sInfo.existsByUsername(name)) {
            return new ResponseEntity<>(new MessageResponse("Usuario no existe"), HttpStatus.NOT_FOUND);
        }
        Info info = sInfo.getByUsername(name);
        InfoDto infoDto = infoMapper.toDto(info);
        infoDto.setUsername(name);
        return new ResponseEntity<>(infoDto, HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/{username}")
    public ResponseEntity<?> delete(@PathVariable("username") String username) {
        if (!sInfo.existsByUsername(username)) {
            return new ResponseEntity<>(new MessageResponse("no existe"), HttpStatus.NOT_FOUND);
        }
        sInfo.delete(username);
        return new ResponseEntity<>(new MessageResponse("Persona eliminada"), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody InfoDto infoDto) throws Exception {
        if((infoDto.getNombre()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        sInfo.save(infoMapper.toInfo(infoDto));
        return new ResponseEntity<>(new MessageResponse("Persona agregada"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PutMapping
    //Los campos no pueden estar vacios de todas maneras por los @NotNull del dto
    public ResponseEntity<?> update(@RequestBody InfoDto infoDto){
        if((infoDto.getNombre()).isEmpty()){
            return new ResponseEntity<>(new MessageResponse("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Info info = sInfo.getByUsername(infoDto.getUsername());
        
        info.setNombre(infoDto.getNombre());
        info.setApellido(infoDto.getApellido());
        info.setDescripcion(infoDto.getDescripcion());
        info.setImg(infoDto.getImg());

        sInfo.update(info);
        return new ResponseEntity<>(new MessageResponse("Persona actualizada"), HttpStatus.OK);
    } 
}