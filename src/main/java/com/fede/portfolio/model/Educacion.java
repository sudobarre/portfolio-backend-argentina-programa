/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.annotation.Documented;

@Entity
@Data
@NoArgsConstructor
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreE;
    private String descripcionE;
    private String desdehastaE;

    public Educacion(String nombreE, String descripcionE, String desdehastaE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.desdehastaE = desdehastaE;
    }
}