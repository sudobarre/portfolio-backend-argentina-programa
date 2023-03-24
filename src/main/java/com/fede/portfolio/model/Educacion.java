/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fede.portfolio.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.lang.annotation.Documented;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Educacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 250, name = "nombre_e", nullable = false)
    private String nombreE;

    @Column(length = 500, name = "desc_e", nullable = false)
    private String descripcionE;
    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    //@Column(name = "desde_e", columnDefinition = "DATE", nullable = false)
    private String desdeE;

    //@DateTimeFormat(pattern = "dd-MM-yyyy")
    //@Column(name = "hasta_e",  columnDefinition = "DATE", nullable = false)
    private String hastaE;

    @Column(length = 1000, name = "imgurl_e")
    private String imgUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Educacion(String nombreE, String descripcionE, String desdeE, String hastaE, String imgUrl, User user) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.desdeE = desdeE;
        this.hastaE = hastaE;
        this.imgUrl = imgUrl;
        this.user = user;
    }
}