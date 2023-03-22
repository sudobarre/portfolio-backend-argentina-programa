
package com.fede.portfolio.service;


import com.fede.portfolio.model.Info;
import com.fede.portfolio.repository.RPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SPersona {
    @Autowired
    RPersona ipersonaRepository;

    public List<Info> getAll(){
         return ipersonaRepository.findAll();
     }
     
     public Info getOne(Long id){

        return ipersonaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No such Persona with id " + id));
     }
     
     public Info getByNombre(String nombre){

        return ipersonaRepository.findByNombre(nombre)
                .orElseThrow(() -> new IllegalStateException("No such Persona with name: " + nombre));
     }
     
     public void save(Info info){
         ipersonaRepository.save(info);
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