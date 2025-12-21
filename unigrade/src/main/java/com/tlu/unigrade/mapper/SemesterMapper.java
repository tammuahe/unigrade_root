package com.tlu.unigrade.mapper;

import com.tlu.unigrade.dto.semester.SemesterDTO;
import com.tlu.unigrade.entity.Semester;

public class SemesterMapper {

    public static SemesterDTO toDto(Semester semester) {
        if (semester == null) return null;

        SemesterDTO dto = new SemesterDTO();
        dto.setId(semester.getId());
        dto.setYear(semester.getYear());
        dto.setSemesterNumber(semester.getSemesterNumber());
        return dto;
    }

    public static Semester toEntity(SemesterDTO dto) {
        if (dto == null) return null;

        Semester semester = new Semester();
        semester.setId(dto.getId());
        semester.setYear(dto.getYear());
        semester.setSemesterNumber(dto.getSemesterNumber());
        return semester;
    }
}
