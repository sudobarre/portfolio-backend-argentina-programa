package com.fede.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExperienciaDto {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    @NotBlank
    private String desdehastaE;

    //Constructor

    public ExperienciaDto() {
    }

    public ExperienciaDto(String nombreE, String descripcionE, String desdehastaE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.desdehastaE = desdehastaE;

    }
}
