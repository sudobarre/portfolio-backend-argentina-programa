
package com.fede.portfolio.service;


import com.fede.portfolio.model.Persona;
import com.fede.portfolio.repository.RPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SPersona {
    @Autowired
    RPersona ipersonaRepository;

    public List<Persona> getAll(){
         return ipersonaRepository.findAll();
     }
     
     public Persona getOne(Long id){

        return ipersonaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No such Persona with id " + id));
     }
     
     public Persona getByNombre(String nombre){

        return ipersonaRepository.findByNombre(nombre)
                .orElseThrow(() -> new IllegalStateException("No such Persona with name: " + nombre));
     }
     
     public void save(Persona persona){
         ipersonaRepository.save(persona);
     }
     
     public void delete(Long id){
         ipersonaRepository.deleteById(id);
     }
     
     public boolean existsById(Long id){
         return ipersonaRepository.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return ipersonaRepository.existsByNombre(nombre);
     }
}