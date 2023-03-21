package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.ExperienciaDto;
import com.fede.portfolio.model.Experiencia;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
public class ExperienciaMapper {
    public Experiencia mapToExperiencia(ExperienciaDto ExperienciaDto) {
        return new Experiencia(
                ExperienciaDto.getNombreE(),
                ExperienciaDto.getDescripcionE(),
                ExperienciaDto.getDesdehastaE()
        );
    }


    public ExperienciaDto mapToExperienciaDto(Experiencia experiencia) {
        return new ExperienciaDto(
                experiencia.getNombreE(),
                experiencia.getDescripcionE(),
                experiencia.getDesdehastaE()
        );
    }
}
