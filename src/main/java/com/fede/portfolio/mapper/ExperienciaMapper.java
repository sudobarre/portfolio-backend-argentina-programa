package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.ExperienciaDto;
import com.fede.portfolio.model.Experiencia;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
public class ExperienciaMapper {

    public ExperienciaDto mapToDto(Experiencia experiencia) {
        return new ExperienciaDto(
                experiencia.getId(),
                experiencia.getNombreE(),
                experiencia.getDescripcionE(),
                experiencia.getDesdeE(),
                experiencia.getHastaE()
        );
    }
}
