package com.fede.portfolio.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoDto {

    @Nullable
    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String nombre;
    @Nullable
    private String apellido;
    @Nullable
    private String img;
    @Nullable
    private String descripcion;

}
