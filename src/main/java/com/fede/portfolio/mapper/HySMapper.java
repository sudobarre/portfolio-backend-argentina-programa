package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.HySDto;
import com.fede.portfolio.model.HyS;
import lombok.Data;
import org.springframework.stereotype.Component;


@Component
public class HySMapper {
    public HySDto mapToDto(HyS hys) {
        HySDto hySDto = new HySDto();
        hySDto.setNombre(hys.getNombre());
        hySDto.setPorcentaje(hys.getPorcentaje());
        hySDto.setSubtitle(hys.getSubtitle());
        return hySDto;
    }
}
