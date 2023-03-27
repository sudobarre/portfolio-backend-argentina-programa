
package com.fede.portfolio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Proyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombreP;
    private String descripcionP;
    private String linkP;

    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Proyecto(Long id, String nombreP, String descripcionP, String linkP, String imgUrl) {
        this.id = id;
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.linkP = linkP;
        this.imgUrl = imgUrl;
    }
}