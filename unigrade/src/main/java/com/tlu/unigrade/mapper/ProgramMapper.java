package com.tlu.unigrade.mapper;

import com.tlu.unigrade.dto.program.ProgramDTO;
import com.tlu.unigrade.entity.Program;

public final class ProgramMapper {

    public ProgramDTO toDto(Program entity) {
        if (entity == null)
            return null;

        ProgramDTO dto = new ProgramDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }

    public  Program toEntity(ProgramDTO dto) {
        if (dto == null)
            return null;

        Program entity = new Program();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        return entity;
    }
}
