package com.tlu.unigrade.mapper;

import com.tlu.unigrade.dto.lecturer.LecturerDTO;
import com.tlu.unigrade.entity.Lecturer;

public class LecturerMapper {

    public static LecturerDTO toDto(Lecturer lecturer) {
        if (lecturer == null) return null;

        LecturerDTO dto = new LecturerDTO();
        dto.setId(lecturer.getId());
        dto.setName(lecturer.getName());
        dto.setDepartment(lecturer.getDepartment());
        return dto;
    }

    public static Lecturer toEntity(LecturerDTO dto) {
        if (dto == null) return null;

        Lecturer lecturer = new Lecturer();
        lecturer.setId(dto.getId());
        lecturer.setName(dto.getName());
        lecturer.setDepartment(dto.getDepartment());
        return lecturer;
    }
}
