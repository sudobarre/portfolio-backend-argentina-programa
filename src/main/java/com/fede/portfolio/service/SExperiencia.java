
package com.fede.portfolio.service;


import com.fede.portfolio.dto.ExperienciaDto;
import com.fede.portfolio.mapper.ExperienciaMapper;
import com.fede.portfolio.model.Experiencia;
import com.fede.portfolio.repository.RExperiencia;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SExperiencia {
     @Autowired
     RExperiencia rExperiencia;

     @Autowired
    ExperienciaMapper experienciaMapper;
     
     public List<ExperienciaDto> list(){
         return rExperiencia.findAll()
                 .stream()
                 .map(experiencia -> experienciaMapper.mapToExperienciaDto(experiencia))
                 .collect(Collectors.toList());
     }
     
     public Experiencia getOne(Long id){
         return rExperiencia.findById(id)
                 .orElseThrow(() -> new IllegalArgumentException("No such experience with id " + id));
     }
     
     public Experiencia getByNombreE(String nombreE){

         return rExperiencia.findByNombreE(nombreE)
                 .orElseThrow(() -> new IllegalArgumentException("No such experience with name " + nombreE));
     }
     
     public void save(Experiencia expe){
         rExperiencia.save(expe);
     }
     
     public void delete(Long id){
         rExperiencia.deleteById(id);
     }
     
     public boolean existsById(Long id){
         return rExperiencia.existsById(id);
     }
     
     public boolean existsByNombreE(String nombreE){
         return rExperiencia.existsByNombreE(nombreE);
     }
}