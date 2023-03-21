/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.repository;


import com.fede.portfolio.model.HyS;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Rhys extends JpaRepository<HyS, Long> {
    Optional<HyS> findByNombre(String nombre);
    public boolean existsByNombre(String nombre);
}