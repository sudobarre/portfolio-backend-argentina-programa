package com.fede.portfolio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducacionDto {
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    @NotBlank
    private String desdehastaE;

    public EducacionDto() {
    }

    public EducacionDto(String nombreE, String descripcionE, String desdehastaE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.desdehastaE = desdehastaE;
    }
}
