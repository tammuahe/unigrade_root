package com.tlu.unigrade.mapper;

import com.tlu.unigrade.dto.grade.GradeDTO;
import com.tlu.unigrade.entity.Grade;

public class GradeMapper {

    public static GradeDTO toDto(Grade grade) {
        if (grade == null) return null;

        GradeDTO dto = new GradeDTO();
        dto.setId(grade.getId());
        dto.setContinuousGrade(grade.getContinuousGrade());
        dto.setMidtermGrade(grade.getMidtermGrade());
        dto.setFinalGrade(grade.getFinalGrade());
        return dto;
    }

    public static Grade toEntity(GradeDTO dto) {
        if (dto == null) return null;

        Grade grade = new Grade();
        grade.setId(dto.getId());
        grade.setContinuousGrade(dto.getContinuousGrade());
        grade.setMidtermGrade(dto.getMidtermGrade());
        grade.setFinalGrade(dto.getFinalGrade());
        return grade;
    }
}
