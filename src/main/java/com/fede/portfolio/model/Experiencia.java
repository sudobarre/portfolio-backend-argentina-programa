
package com.fede.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreE;
    private String descripcionE;
    private String desdeE;
    private String hastaE;
    private String imgUrl = "";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Experiencia(String nombreE, String descripcionE, String desdeE, String hastaE, String imgUrl, User user) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.desdeE = desdeE;
        this.hastaE = hastaE;
        this.imgUrl = imgUrl;
        this.user = user;
    }

    public Experiencia(String nombreE, String descripcionE, String desdeE, String hastaE, String imgUrl) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.desdeE = desdeE;
        this.hastaE = hastaE;
        this.imgUrl = imgUrl;
    }
}