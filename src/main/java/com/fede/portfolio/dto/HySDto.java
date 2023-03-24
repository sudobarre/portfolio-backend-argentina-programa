package com.fede.portfolio.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class HySDto {
    @Nullable
    private Long id;
    @NotBlank
    private String nombre;
    @NotBlank
    private int porcentaje;
    @NotBlank
    private String subtitle;
    @Nullable
    private String imgUrl;

    public HySDto() {
    }

}
