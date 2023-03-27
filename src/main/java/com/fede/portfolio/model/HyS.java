/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class HyS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int porcentaje;
    private String subtitle;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public HyS(String nombre, int porcentaje, String subtitle, User user) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.subtitle = subtitle;
        this.user = user;
    }

    public HyS(String nombre, int porcentaje, String subtitle, String imgUrl, User user) {
        this.nombre = nombre;
        this.porcentaje = porcentaje;
        this.subtitle = subtitle;
        this.imgUrl = imgUrl;
        this.user = user;
    }
}