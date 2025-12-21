package com.tlu.unigrade.mapper;

import org.springframework.stereotype.Component;

import com.tlu.unigrade.dto.lecturer.LecturerDTO;
import com.tlu.unigrade.entity.Lecturer;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class LecturerMapper {

    public LecturerDTO toDto(Lecturer lecturer) {
        if (lecturer == null) return null;

        LecturerDTO dto = new LecturerDTO();
        dto.setId(lecturer.getId());
        dto.setName(lecturer.getName());
        dto.setDepartment(lecturer.getDepartment());
        return dto;
    }

    public Lecturer toEntity(LecturerDTO dto) {
        if (dto == null) return null;

        Lecturer lecturer = new Lecturer();
        lecturer.setId(dto.getId());
        lecturer.setName(dto.getName());
        lecturer.setDepartment(dto.getDepartment());
        return lecturer;
    }
}
