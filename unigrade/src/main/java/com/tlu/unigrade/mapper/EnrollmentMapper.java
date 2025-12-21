package com.tlu.unigrade.mapper;

import org.springframework.stereotype.Component;

import com.tlu.unigrade.dto.enrollment.EnrollmentDTO;
import com.tlu.unigrade.entity.Enrollment;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnrollmentMapper {
    private final CourseMapper courseMapper;
    private final GradeMapper gradeMapper;
    private final SemesterMapper semesterMapper;
    private final LecturerMapper lecturerMapper;

    public EnrollmentDTO toDto(Enrollment enrollment) {
        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getId());
        dto.setCourse(courseMapper.toDto(enrollment.getSection().getCourse()));
        dto.setSemester(semesterMapper.toDto(enrollment.getSection().getSemester()));
        dto.setLecturer(lecturerMapper.toDto(enrollment.getSection().getLecturer()));
        dto.setGrade(gradeMapper.toDto(enrollment.getGrade()));
        dto.setStatus(enrollment.getStatus());
        return dto;
    }
}
