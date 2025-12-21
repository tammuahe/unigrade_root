package com.tlu.unigrade.mapper;

import com.tlu.unigrade.dto.student.StudentDTO;
import com.tlu.unigrade.entity.Student;

public final class StudentMapper {

    private ProgramMapper programMapper;

    public StudentDTO toDto(Student entity) {
        if (entity == null)
            return null;

        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setDateOfBirth(entity.getDateOfBirth());
        dto.setStatus(entity.getStatus());
        dto.setProgram(programMapper.toDto(entity.getProgram()));
        return dto;
    }

    public Student toEntity(StudentDTO dto) {
        if (dto == null)
            return null;

        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setDateOfBirth(dto.getDateOfBirth());
        entity.setStatus(dto.getStatus());
        entity.setProgram(programMapper.toEntity(dto.getProgram()));
        return entity;
    }
}
