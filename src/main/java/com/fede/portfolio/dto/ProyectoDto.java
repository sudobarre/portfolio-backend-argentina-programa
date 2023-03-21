package com.fede.portfolio.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
public class ProyectoDto {
    @NotBlank
    private String nombreP;
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String linkP;

    //Constructor

    public ProyectoDto() {
    }

    public ProyectoDto(String nombreP, String descripcionP, String linkP) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.linkP = linkP;

    }
}
