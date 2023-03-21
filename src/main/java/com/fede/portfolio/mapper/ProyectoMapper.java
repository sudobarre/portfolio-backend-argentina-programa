package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.ProyectoDto;
import com.fede.portfolio.model.Proyecto;
import org.springframework.stereotype.Component;

@Component
public class ProyectoMapper {
    public ProyectoDto toDto(Proyecto proyecto) {
        ProyectoDto proyectoDto = new ProyectoDto();
        proyectoDto.setNombreP(proyecto.getNombreP());
        proyectoDto.setDescripcionP(proyecto.getDescripcionP());
        proyectoDto.setLinkP(proyecto.getLinkP());
        return proyectoDto;
    }

    public Proyecto toProyecto(ProyectoDto dtoproy) {
        Proyecto proyecto = new Proyecto();
        proyecto.setNombreP(dtoproy.getNombreP());
        proyecto.setDescripcionP(dtoproy.getDescripcionP());
        proyecto.setLinkP(dtoproy.getLinkP());
        return proyecto;
    }
}
