
package com.fede.portfolio.service;


import com.fede.portfolio.dto.EducacionDto;
import com.fede.portfolio.model.Educacion;
import com.fede.portfolio.model.User;
import com.fede.portfolio.repository.REducacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SEducacion {
    @Autowired
    REducacion rEducacion;

    @Autowired
    AuthService authService;
    
    public List<Educacion> getAll(){
        User user = authService.getCurrentUser();
        return rEducacion.findAllByUser(user);
    }
    
    public Educacion getOne(Long id){
        User user = authService.getCurrentUser();
        return rEducacion.findByUserAndId(user, id)
                .orElseThrow(() -> new IllegalArgumentException("No such education with id:" + id + "found."));
    }

    
    public void save(EducacionDto dtoeduc){
        User user = authService.getCurrentUser();
        Educacion educacion = new Educacion(
                dtoeduc.getNombreE(),
                dtoeduc.getDescripcionE(),
                dtoeduc.getDesdeE(),
                dtoeduc.getHastaE(),
                dtoeduc.getImgUrl(),
                user
        );
        rEducacion.save(educacion);
    }
    
    public void delete(Long id){
        rEducacion.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return rEducacion.existsById(id);
    }
    
    public boolean existsByNombreE(String nombreE){
        return rEducacion.existsByNombreE(nombreE);
    }

    public void update(Educacion educacion) {
        User user = authService.getCurrentUser();
        educacion.setUser(user);
        rEducacion.save(educacion);
    }
}