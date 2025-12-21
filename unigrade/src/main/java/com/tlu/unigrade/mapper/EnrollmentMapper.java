package com.tlu.unigrade.mapper;

import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.entity.Enrollment;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EnrollmentMapper {
    private CourseMapper courseMapper;

    public EnrollmentDTO toDto(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setCourse(courseMapper.toDto(enrollment.getSection().getCourse()));
        dto.setSemester(SemesterMapper.toDto(enrollment.getSection().getSemester()));
        dto.setLecturer(LecturerMapper.toDto(enrollment.getSection().getLecturer()));
        dto.setGrade(GradeMapper.toDto(enrollment.getGrade()));
        dto.setStatus(enrollment.getStatus());
        return dto;
    }
}
