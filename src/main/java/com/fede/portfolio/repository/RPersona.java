/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.fede.portfolio.repository;


import com.fede.portfolio.model.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RPersona extends JpaRepository<Info,Long> {
    public Optional<Info> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}
