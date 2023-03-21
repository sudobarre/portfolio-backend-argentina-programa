package com.fede.portfolio.mapper;

import com.fede.portfolio.dto.PersonaDto;
import com.fede.portfolio.model.Persona;
import org.springframework.stereotype.Component;

@Component
public class PersonaMapper {
    public PersonaDto toDto(Persona persona) {
        PersonaDto personaDto = new PersonaDto();
        personaDto.setNombre(persona.getNombre());
        personaDto.setApellido(persona.getApellido());
        personaDto.setDescripcion(persona.getDescripcion());
        personaDto.setImg(persona.getImg());
        return personaDto;
    }

    public Persona toPersona(PersonaDto personaDto) {
        return new Persona(
                personaDto.getNombre(),
                personaDto.getDescripcion(),
                personaDto.getApellido(),
                personaDto.getImg()
        );
    }
}
