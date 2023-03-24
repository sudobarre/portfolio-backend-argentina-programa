/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.service;


import com.fede.portfolio.dto.HySDto;
import com.fede.portfolio.model.HyS;
import com.fede.portfolio.model.User;
import com.fede.portfolio.repository.Rhys;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
public class Shys {
    @Autowired
    Rhys rhys;

    @Autowired
    AuthService authService;
    
    public List<HyS> getAll(){
        User user = authService.getCurrentUser();
        return rhys.findAllByUser(user);
    }
    
    public HyS getOne(Long id){
        return rhys.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No such id: " + id));
    }
    
    public HyS getByNombre(String nombre){
        return rhys.findByNombre(nombre)
                .orElseThrow(() -> new IllegalArgumentException("No such name: " + nombre));

    }
    
    public void save(HySDto dtohys){
        User user = authService.getCurrentUser();
        HyS hYs = new HyS(
                dtohys.getNombre(),
                dtohys.getPorcentaje(),
                dtohys.getSubtitle(),
                dtohys.getImgUrl(),
                user);
        rhys.save(hYs);
    }
    
    public void delete(Long id){
        rhys.deleteById(id);
    }
    
    public boolean existsById(Long id){
        return rhys.existsById(id);
    }
    
    public boolean existsByNombre(String nombre){
        return rhys.existsByNombre(nombre);
    }

    public void update(HyS hYs) {
        rhys.save(hYs);
    }
}