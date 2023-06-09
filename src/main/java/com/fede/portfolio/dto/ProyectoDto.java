package com.fede.portfolio.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
public class ProyectoDto {
    @Nullable
    private Long id;
    @NotBlank
    private String nombreP;
    @NotBlank
    private String descripcionP;
    @NotBlank
    private String linkP;

    @Nullable
    private String imgUrl;

    //Constructor

    public ProyectoDto() {
    }

    public ProyectoDto(String nombreP, String descripcionP, String linkP, String imgUrl) {
        this.nombreP = nombreP;
        this.descripcionP = descripcionP;
        this.linkP = linkP;
        this.imgUrl = imgUrl;
    }
}
