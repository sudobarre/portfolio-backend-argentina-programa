package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.EducacionDto;
import com.fede.portfolio.model.Educacion;
import com.fede.portfolio.model.User;
import com.fede.portfolio.service.AuthService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class EducacionMapper {

    public EducacionDto mapToDto(Educacion educacion) {
        EducacionDto educacionDto = new EducacionDto();
        educacionDto.setNombreE(educacion.getNombreE());
        educacionDto.setDescripcionE(educacion.getDescripcionE());
        educacionDto.setDesdeE(educacion.getDesdeE());
        educacionDto.setHastaE(educacion.getHastaE());
        return educacionDto;
    }
}
