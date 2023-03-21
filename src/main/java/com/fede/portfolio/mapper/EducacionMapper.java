package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.EducacionDto;
import com.fede.portfolio.model.Educacion;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
public class EducacionMapper {
    public EducacionDto mapToDto(Educacion educacion) {
        EducacionDto educacionDto = new EducacionDto();
        educacionDto.setNombreE(educacion.getNombreE());
        educacionDto.setDescripcionE(educacion.getDescripcionE());
        educacionDto.setDesdehastaE(educacion.getDesdehastaE());
        return educacionDto;
    }
}
