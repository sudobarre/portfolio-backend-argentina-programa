
package com.fede.portfolio.service;

import com.fede.portfolio.model.Proyecto;
import com.fede.portfolio.repository.RProyecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SProyecto {
     @Autowired
     RProyecto rProyecto;
     
     public List<Proyecto> getAll(){
         return rProyecto.findAll();
     }
     
     public Proyecto getOne(Long id){
         return rProyecto.findById(id)
                 .orElseThrow(()->new IllegalArgumentException("No such Proyecto with id " + id));
     }
     
     public Proyecto getByNombreP(String nombreP){
         return rProyecto.findByNombreP(nombreP)
                 .orElseThrow(()->new IllegalArgumentException("No such Proyecto with nombre: " + nombreP));
     }
     
     public void save(Proyecto proy){
         rProyecto.save(proy);
     }
     
     public void delete(Long id){
         rProyecto.deleteById(id);
     }
     
     public boolean existsById(Long id){
         return rProyecto.existsById(id);
     }
     
     public boolean existsByNombreP(String nombreP){
         return rProyecto.existsByNombreP(nombreP);
     }
}