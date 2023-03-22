package com.fede.portfolio.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EducacionDto {
    @Nullable
    private Long id;
    @NotBlank
    private String nombreE;
    @NotBlank
    private String descripcionE;
    @NotBlank
    private String desdeE;

    @NotBlank
    private String hastaE;

    public EducacionDto() {
    }

    public EducacionDto(String nombreE, String descripcionE, String desdeE, String hastaE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
        this.desdeE = desdeE;
        this.hastaE = hastaE;
    }
}
