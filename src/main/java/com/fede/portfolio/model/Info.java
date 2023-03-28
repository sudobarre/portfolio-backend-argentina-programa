
package com.fede.portfolio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String nombre;

    private String apellido;
    @NotNull
    private String img;
    @NotNull
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}