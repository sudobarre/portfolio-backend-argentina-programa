/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.service;


import com.fede.portfolio.model.Educacion;
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
    
    public List<Educacion> getAll(){
        return rEducacion.findAll();
    }
    
    public Educacion getOne(Long id){

        return rEducacion.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No such education with id:" + id + "found."));
    }
    
    public Educacion getByNombreE(String nombreE){
        return rEducacion.findByNombreE(nombreE)
                .orElseThrow(() -> new IllegalArgumentException("No such education with name:" + nombreE + "found."));
    }
    
    public void save(Educacion educacion){
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
}