package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.InfoDto;
import com.fede.portfolio.model.Info;
import org.springframework.stereotype.Component;

@Component
public class InfoMapper {
    public InfoDto toDto(Info info) {
        InfoDto infoDto = new InfoDto();
        infoDto.setNombre(info.getNombre());
        infoDto.setApellido(info.getApellido());
        infoDto.setDescripcion(info.getDescripcion());
        infoDto.setImg(info.getImg());
        return infoDto;
    }

    public Info toInfo(InfoDto infoDto) {
        if (infoDto == null) {
            return null;
        }
        if (infoDto.getId() == null) return null;
        Info info = new Info();
        info.setId(infoDto.getId());
        info.setNombre(infoDto.getNombre());
        info.setApellido(infoDto.getApellido());
        info.setDescripcion(infoDto.getDescripcion());
        info.setImg(infoDto.getImg());
        return info;
    }
}
