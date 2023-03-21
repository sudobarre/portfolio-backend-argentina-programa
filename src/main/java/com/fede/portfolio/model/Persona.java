
package com.fede.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String nombre;
    
    @NotNull
    private String apellido;
    
    @NotNull
    private String img;
    
    @NotNull
    private String descripcion;


    public Persona(String nombre, String descripcion, String apellido, String img) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.apellido = apellido;
        this.img = img;
    }
}