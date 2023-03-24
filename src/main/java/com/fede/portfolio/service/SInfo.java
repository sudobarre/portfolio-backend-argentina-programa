
package com.fede.portfolio.service;


import com.fede.portfolio.exceptions.UserNotFoundException;
import com.fede.portfolio.model.Info;
import com.fede.portfolio.model.User;
import com.fede.portfolio.repository.RInfo;
import com.fede.portfolio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SInfo {
    @Autowired
    RInfo ipersonaRepository;

    @Autowired
    AuthService authService;

    @Autowired
    UserRepository userRepository;

     public Info getById(Long id){
        return ipersonaRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("No such Persona with id:" + id));
     }
     
     public Info getByUsername(String name){
         User user = userRepository.findByUsername(name)
                 .orElseThrow(() -> new UserNotFoundException("User with name:" + name  + "does not exist"));

         return ipersonaRepository.findByUser(user)
                 .orElseThrow(() -> new IllegalStateException("No such Persona with name" + name));
     }
     
     public void save(Info info){
        User user = authService.getCurrentUser();
        info.setUser(user);
        ipersonaRepository.save(info);
     }
     
     public void delete(String username){
         User user = authService.getCurrentUser();

         ipersonaRepository.deleteByUser(user);
     }
     
     public boolean existsById(Long id){
         return ipersonaRepository.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return ipersonaRepository.existsByNombre(nombre);
     }

    public void update(Info info) {
        ipersonaRepository.save(info);
    }

    public boolean existsByUsername(String name) {
         return userRepository.existsByUsername(name);
    }
}