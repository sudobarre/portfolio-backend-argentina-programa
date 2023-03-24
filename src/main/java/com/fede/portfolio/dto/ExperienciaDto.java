package com.fede.portfolio.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExperienciaDto {

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

    @Nullable
    private String imgUrl;

    public ExperienciaDto() {
    }



}
