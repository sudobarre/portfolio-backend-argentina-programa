package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.ProyectoDto;
import com.fede.portfolio.model.Proyecto;
import org.springframework.stereotype.Component;

@Component
public class ProyectoMapper {
    public ProyectoDto toDto(Proyecto proyecto) {
        ProyectoDto proyectoDto = new ProyectoDto();
        proyectoDto.setImgUrl(proyecto.getImgUrl());
        proyectoDto.setId(proyecto.getId());
        proyectoDto.setNombreP(proyecto.getNombreP());
        proyectoDto.setDescripcionP(proyecto.getDescripcionP());
        proyectoDto.setLinkP(proyecto.getLinkP());
        return proyectoDto;
    }

}
