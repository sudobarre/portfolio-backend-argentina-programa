
package com.fede.portfolio.service;


import com.fede.portfolio.dto.ExperienciaDto;
import com.fede.portfolio.mapper.ExperienciaMapper;
import com.fede.portfolio.model.Experiencia;
import com.fede.portfolio.model.User;
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

     @Autowired
     AuthService authService;
     
     public List<ExperienciaDto> getAll(){
         User user = authService.getCurrentUser();
         return rExperiencia.findAllByUser(user)
                 .stream()
                 .map(experiencia -> experienciaMapper.mapToDto(experiencia))
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
     
     public void save(ExperienciaDto dtoexp){
         User user = authService.getCurrentUser();
         Experiencia experiencia = new Experiencia(
                 dtoexp.getNombreE(),
                 dtoexp.getDescripcionE(),
                 dtoexp.getDesdeE(),
                 dtoexp.getHastaE(),
                 dtoexp.getImgUrl(),
                 user
         );
         rExperiencia.save(experiencia);
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

    public void update(Experiencia experiencia) {
        User user = authService.getCurrentUser();
        experiencia.setUser(user);
        rExperiencia.save(experiencia);
    }
}